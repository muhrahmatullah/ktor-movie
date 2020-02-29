package dev.rahmat.movie.routes

import dev.rahmat.movie.entity.Movie
import dev.rahmat.movie.entity.Response
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.request.receive
import io.ktor.response.header
import io.ktor.response.respond
import io.ktor.routing.*
import kotlin.random.Random

fun Route.movies(movies: List<Movie>) {
    get("/movies") {
        // this is example to add header in the response
        call.response.header("x-id", "mattku")
        // this is example to modify status code in the response
        // it will use 200 as the default
        call.response.status(value = HttpStatusCode.OK)
        call.respond(Response(data = movies, code = 200, message = "OK"))
    }
}

fun Route.movieById(movies: List<Movie>) {
    get("/movie/{id}") {
        val id = call.parameters["id"].toString()
        val selectedMovie = movies.find { movie -> movie.id == id }

        if (selectedMovie != null) {
            call.respond(Response(data = selectedMovie, code = 200, message = "OK"))
        } else {
            // this is example to modify status code in the response and assign the value to the response wrapper
            call.response.status(value = HttpStatusCode.BadRequest)
            call.respond(Response(data = null, code = call.response.status()?.value, message = "Movie not found"))
        }

    }
}

fun Route.deleteById(movies: MutableList<Movie>) {
    delete("/movie/{id}") {
        val id = call.parameters["id"].toString()

        val existToBeDeleted = movies.removeIf {
            it.id == id
        }

        if (existToBeDeleted) {
            call.respond(Response(data = movies, code = 200, message = "OK"))
        } else {
            // this is example to modify status code in the response and assign the value to the response wrapper
            call.response.status(value = HttpStatusCode.BadRequest)
            call.respond(Response(data = null, code = call.response.status()?.value, message = "Movie not found"))
        }

    }
}

fun Route.createMovie(movies: MutableList<Movie>) {
    post("/movie") {
        val id = Random.nextInt(1, 100000)
        println("header ${call.request.headers["Content-Type"]}")
        if(movies.find { it.id == id.toString() } != null) {
            // this is example to modify status code in the response and assign the value to the response wrapper
            call.response.status(value = HttpStatusCode.BadRequest)
            call.respond(Response(data = null, code = call.response.status()?.value, message = "Movie with $id already exist"))
        } else {
            // this is possible because we already install negotiation which we use GSON for json deserialization
            val payload = call.receive(Movie::class).copy(id = id.toString())
            println(payload)
            movies.add(payload)
            call.respond(Response(data = movies, code = 200, message = "OK"))
        }
    }
}

fun Route.editMovie(movies: MutableList<Movie>) {
    put("/movie/{id}") {
        val id = call.parameters["id"].toString()
        val editedIndex = movies.indexOfFirst { it.id == id}
        if(editedIndex != -1) {
            val payload = call.receive(Movie::class).copy(id = id)
            // remove element first, this is used to maintain the position of edited element inside the list
            movies.removeAt(editedIndex)
            // then add new one in the index
            movies.add(editedIndex, payload)
            call.respond(Response(data = movies, code = 200, message = "OK"))
        } else {
            // this is possible because we already install negotiation which we use GSON for json deserialization
            call.response.status(value = HttpStatusCode.BadRequest)
            call.respond(Response(data = null, code = call.response.status()?.value, message = "Movie not found"))
        }
    }
}
