package com.example.crypto.market.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException
import javax.servlet.annotation.HttpConstraint

@ResponseStatus(HttpStatus.FORBIDDEN)
class NotEnoughMoneyException(msg: String) : RuntimeException(msg) {
}