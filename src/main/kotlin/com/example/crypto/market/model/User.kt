package com.example.crypto.market.model

import org.hibernate.annotations.Cascade
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class User (
    @Id
    @GeneratedValue
    val id: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    @OneToOne
    @Cascade
    val wallet: Wallet = Wallet()
)