package com.fireblade.repository.model

sealed class Error {
    object GeneralError: Error()
}