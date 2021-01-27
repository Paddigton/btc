package com.example.crypto.market.controller

import com.example.crypto.market.CryptoFacade
import com.example.crypto.market.exceptions.UserNotFoundExceptions
import com.example.crypto.market.model.User
import com.example.crypto.market.model.Wallet
import com.example.crypto.market.repository.UserRepository
import com.example.crypto.market.repository.WalletRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class CryptoController {

    @Autowired
    lateinit var cryptoFacade: CryptoFacade


    @GetMapping("/users")
    fun getUsers(): List<User>{
        return cryptoFacade.getUsers()
    }

    @PostMapping("/user")
    fun addUser(@RequestParam("firstName") firstName: String,
                @RequestParam("lastName") lastName: String): User{
        return cryptoFacade.addUser(firstName, lastName)
    }

    @GetMapping("/user/{id}")
    fun getUser(@PathVariable id: Long): User {
        return cryptoFacade.getUser(id)
    }

    @PostMapping("/user/{id}/sell/btc")
    fun sellBtc(@PathVariable id: Long,
                @RequestParam("amount") amount: Double): Wallet {
        return cryptoFacade.sellBtc(id, amount)
    }

    @PostMapping("/user/{id}/buy/btc")
    fun buyBtc(@PathVariable id: Long,
                @RequestParam("amount") amount: Double): Wallet {
        return cryptoFacade.buyBtc(id, amount)
    }
}