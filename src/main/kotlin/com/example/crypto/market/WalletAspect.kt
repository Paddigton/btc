package com.example.crypto.market

import com.example.crypto.market.exceptions.WrongAmountException
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.springframework.stereotype.Component

@Aspect
@Component
class WalletAspect {

    @Before("execution(*com.example.crypto.market.controller.CryptoController.sellBtc(..)) && args(id, amount)")
    fun validateTransaction(id: Long, amount: Double){

        if(amount < 0){
            throw WrongAmountException()
        }
    }


}