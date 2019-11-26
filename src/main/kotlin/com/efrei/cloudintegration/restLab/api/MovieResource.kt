package com.efrei.cloudintegration.restLab.api

import com.efrei.cloudintegration.restLab.domain.Actor
import com.efrei.cloudintegration.restLab.domain.Movie
import com.efrei.cloudintegration.restLab.service.MovieService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.client.RestTemplate
import java.net.URI

@RestController
@RequestMapping("/movies")
class MovieResource(private val movieService : MovieService) {

    private val URL_API : String = "http://localhost:3000"

    @GetMapping("/{id}")
    fun getMovie(@PathVariable id : Long) : ResponseEntity<Movie> {
        return ResponseEntity.of(this.movieService.getMovie(id))
    }

    @GetMapping
    fun getAllMovies() : ResponseEntity<List<Movie>> {
        return ResponseEntity.ok(this.movieService.getAllMovies())
    }

    @PostMapping
    fun postMovie(@RequestBody movie : Movie) : ResponseEntity<Movie> {
        val newMovie = this.movieService.create(movie)
        return ResponseEntity.created(URI.create("/movies/${newMovie!!.id}")).body(movie)
    }

    @PutMapping("/{id}")
    fun putMovie(@RequestBody movie : Movie, @PathVariable id : Long) : ResponseEntity<Movie> {
        movie.id = id
        return ResponseEntity.ok(this.movieService.update(movie))
    }

    @DeleteMapping("/{id}")
    fun deleteMovie(@PathVariable id : Long) {
        return this.movieService.delete(id)
    }


    fun Movie.render(actor : Actor) = RenderedMovie(
            id,
            title,
            actor.render()
    )

    data class RenderedMovie(
            val id: Long,
            val title: String,
            val actor: RenderedActor
    )

    fun Actor.render() = RenderedActor(
            id,
            sexe,
            name,
            age
    )

    data class RenderedActor(
            val id : Long,
            val sexe : String,
            val name : String,
            val age : Int
    )
}