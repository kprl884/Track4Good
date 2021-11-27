package com.track4good.hackathon.domain.entity

import java.io.Serializable

data class Advert(
    val id: String,
    val title: String,
    val description: String,
    val drawableId: Int,
    val advertOwner: User,
    val place: Place
): Serializable