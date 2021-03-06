package com.example.crypto.market.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.RuntimeException

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
class CantFetchBtcPrice: RuntimeException() {
}