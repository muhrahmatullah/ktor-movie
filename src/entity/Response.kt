package dev.rahmat.entity

data class Response<T>(val data: T, val code: Int, val message: String)