package com.example.crypto.market.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class User (
    @Id
    @GeneratedValue
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val wallet: Wallet = Wallet()
)