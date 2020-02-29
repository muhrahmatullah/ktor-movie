package dev.rahmat.movie

import dev.rahmat.movie.entity.Actor
import dev.rahmat.movie.entity.Movie
import dev.rahmat.movie.routes.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.gson.*
import io.ktor.features.*

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {

    val movies = initData()

    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        get("/") {
            call.respondText("A very simple rest api using k-tor!", contentType = ContentType.Text.Plain)
        }
        movies(movies)
        movieById(movies)
        deleteById(movies)
        createMovie(movies)
        editMovie(movies)
    }
}
















fun initData(): MutableList<Movie> {
    val movies = mutableListOf<Movie>()
    val movie = Movie(
        id = "1234",
        title = "Spongebob's epic night",
        rating = 9.8,
        year = 2019,
        actors = listOf(Actor(name = "Spongebob Squarepants", age = 32))
    )

    movies.add(movie)

    movies.add(
        movie.copy(
            id = "123433",
            title = "Patrick the real star",
            rating = 9.6,
            year = 2019,
            actors = listOf(Actor(name = "Patrick Star", age = 42), Actor(name = "Spongebob Squarepants", age = 32))
        )
    )

    movies.add(
        movie.copy(
            id = "1sdf234",
            title = "Squidward's falseto voice",
            rating = 9.6,
            year = 2019,
            actors = listOf(Actor(name = "Squidward Tentacle", age = 32))
        )
    )

    return movies

}

