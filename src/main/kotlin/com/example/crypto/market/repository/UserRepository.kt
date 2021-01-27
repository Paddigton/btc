package com.example.crypto.market.repository

import com.example.crypto.market.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
}