package com.track4good.hackathon.domain.entity

enum class UserType(val value:Int){
    Guess(0),
    Host(1);
    companion object{
        fun valueOfInt(value: Int) = UserType.values().find { it.value == value }
    }
}