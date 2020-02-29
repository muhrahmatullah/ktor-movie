package dev.rahmat.movie.entity

data class Movie(val id: String, val title: String, val rating: Double, val year: Int, val actors: List<Actor>)

data class Actor(val name: String, val age: Int)