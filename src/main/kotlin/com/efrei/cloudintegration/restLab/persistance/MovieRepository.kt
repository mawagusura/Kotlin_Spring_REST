package com.efrei.cloudintegration.restLab.persistance

import com.efrei.cloudintegration.restLab.domain.Movie
import org.springframework.data.jpa.repository.JpaRepository

interface MovieRepository : JpaRepository<Movie, Long> {

    fun findByTitle(title : String) : Movie?
}