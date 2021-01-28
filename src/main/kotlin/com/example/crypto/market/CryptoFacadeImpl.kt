package com.example.crypto.market

import com.example.crypto.market.exceptions.CantFetchBtcPrice
import com.example.crypto.market.exceptions.NotEnoughMoneyException
import com.example.crypto.market.exceptions.UserNotFoundExceptions
import com.example.crypto.market.model.BtcPrice
import com.example.crypto.market.model.Credentials
import com.example.crypto.market.model.User
import com.example.crypto.market.model.Wallet
import com.example.crypto.market.repository.UserRepository
import com.example.crypto.market.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


interface CryptoFacade {
    fun getUsers(): List<User>
    fun addUser(firstName: String, lastName: String): User
    fun getUser(id: Long): User
    fun sellBtc(id: Long, amount: Double): Wallet
    fun buyBtc(id: Long, amount: Double): Wallet
    fun deleteUser(id: Long)
    fun deleteAllusers()
    fun updateFisrtName(id: Long, firstName: String) : User
    fun updateUserCrenetials(id: Long, credentials: Credentials): User
}

@Service
class CryptoFacadeImpl: CryptoFacade {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var walletRepository: WalletRepository


    override fun getUsers(): List<User> {
       return userRepository.findAll()
    }

    override fun addUser(firstName: String, lastName: String): User {
        val wallet = Wallet(btc = 5.0, usd = 1250.0)
        walletRepository.save(wallet)

        val user = User(firstName = firstName, lastName = lastName, wallet = wallet)
        userRepository.save(user)

        return user
    }

    override fun getUser(id: Long): User {
        return userRepository
                .findById(id)
                .orElseThrow{ throw UserNotFoundExceptions("can't find user with such id") }
    }

    override fun sellBtc(id: Long, amount: Double): Wallet {
        val user = userRepository.getOne(id)
        val btcAmount = user.wallet.btc

        val askPrice = getBtcPrice().ask
        if(btcAmount >= amount){
            user.wallet.btc -= amount
            user.wallet.usd += amount * askPrice
            userRepository.save(user)
        }else{
            throw NotEnoughMoneyException("you have not enough bitcoins")
        }
        return user.wallet
    }

    override fun buyBtc(id: Long, amount: Double): Wallet {
        val user = userRepository.getOne(id)
        val usdAmount = user.wallet.usd

        val bidPrice = getBtcPrice().bid
        if(amount * getBtcPrice().ask <= usdAmount){
            user.wallet.usd -= amount * bidPrice
            user.wallet.btc += amount
            userRepository.save(user)
        }else{
            throw NotEnoughMoneyException("you have not enough usd")
        }
        return user.wallet
    }

    override fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

    override fun deleteAllusers() {
        userRepository.deleteAll()
    }

    override fun updateFisrtName(id: Long, firstName: String): User {
        val user = userRepository.getOne(id)
        val newUser = User(user.id, firstName, user.lastName, user.wallet)

        return userRepository.save(newUser)
    }

    override fun updateUserCrenetials(id: Long, credentials: Credentials): User {
        val user = userRepository.getOne(id)
        val updateUser = User(user.id, credentials.firstName, credentials.lastName, user.wallet)

        return userRepository.save(updateUser)
    }

    fun getBtcPrice(): BtcPrice {
        val response = RestTemplate()
                .getForObject(API_GET_BTC_PRICE, BtcPrice::class.java) ?: throw CantFetchBtcPrice()

        return response
    }

    companion object{
        const val BTC_PRICE = 5000.0
        const val API_GET_BTC_PRICE = "https://www.bitstamp.net/api/v2/ticker/btcusd/"
    }
}