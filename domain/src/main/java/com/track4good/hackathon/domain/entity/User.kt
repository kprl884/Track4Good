package com.track4good.hackathon.domain.entity

data class User(
    val id: String,
    var type: UserType,
    val fullName: String,
    val mail: String,
    val birthDate: String,
    val hesCode: String,
    val userPhoto: Int
)