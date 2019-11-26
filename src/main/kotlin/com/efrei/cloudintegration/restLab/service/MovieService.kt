package com.efrei.cloudintegration.restLab.service

import com.efrei.cloudintegration.restLab.domain.Movie
import com.efrei.cloudintegration.restLab.persistance.MovieRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class MovieService(var movieRepository: MovieRepository) {

    fun getMovie(idMovie : Long) : Optional<Movie> = this.movieRepository.findById(idMovie)

    fun getAllMovies() : List<Movie> = this.movieRepository.findAll()

    fun create(movie : Movie) : Movie? = this.movieRepository.save(movie)

    fun delete(id : Long) = this.movieRepository.findById(id).map { m -> this.movieRepository.delete(m) }.orElse(null)

    fun update(movie : Movie) = this.movieRepository.findById(movie.id).map { m -> this.movieRepository.save(movie) }.orElse(null)

}