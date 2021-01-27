package com.example.crypto.market.model

import org.hibernate.annotations.Cascade
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
data class Wallet(
    @Id
    @GeneratedValue
    val id: Long = 0,
    var btc: Double = 0.0,
    @OneToOne
    @Cascade
    var usd: Double = 0.0
)