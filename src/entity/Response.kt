package dev.rahmat.movie.entity

data class Response<T>( val code: Int?, val message: String, val data: T?)