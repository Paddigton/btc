package com.example.crypto.market.repository

import com.example.crypto.market.model.Wallet
import org.springframework.data.jpa.repository.JpaRepository

interface WalletRepository: JpaRepository<Wallet, Long> {
}