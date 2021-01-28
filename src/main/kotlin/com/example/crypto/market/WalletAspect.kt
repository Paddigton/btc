package com.example.crypto.market

import com.example.crypto.market.exceptions.UserNotFoundExceptions
import com.example.crypto.market.exceptions.WrongAmountException
import com.example.crypto.market.model.Credentials
import com.example.crypto.market.repository.UserRepository
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Aspect
@Component
class WalletAspect {

    @Autowired
    lateinit var userRepository: UserRepository

    @Before("execution(* com.example.crypto.market.controller.CryptoController.sellBtc(..)) && args(id, amount)")
    fun validateSellTransaction(id: Long, amount: Double){
        validateAmount(amount)
        validateUserId(id)
    }

    @Before("execution(* com.example.crypto.market.controller.CryptoController.buyBtc(..)) && args(id, amount)")
    fun validateBuyTransaction(id: Long, amount: Double){
        validateAmount(amount)
        validateUserId(id)
    }

    @Before("execution(* com.example.crypto.market.controller.CryptoController.deleteUser(..)) && args(id)")
    fun validateDeleteUser(id: Long){
        validateUserId(id)
    }

    @Before("execution(* com.example.crypto.market.controller.CryptoController.updateUserFirstName(..)) && args(id, firstName)")
    fun validateUpdateFirstName(id: Long, firstName: String){
        validateUserId(id)
    }

    @Before("execution(* com.example.crypto.market.controller.CryptoController.updateUserCredentials(..)) && args(id, credentials)")
    fun validateUpdateCredentials(id: Long, credentials: Credentials){
        validateUserId(id)
    }

    private fun validateAmount(amount: Double){
        if (amount < 0) {
            throw WrongAmountException()
        }
    }

    private fun validateUserId(id: Long){
        if (!userRepository.existsById(id))
            throw UserNotFoundExceptions("there is no user with id ${id}")
    }
}