package com.efrei.cloudintegration.restLab.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Movie (
    var title: String,
    @Id @GeneratedValue var id : Long,
    var actorId : Long
)