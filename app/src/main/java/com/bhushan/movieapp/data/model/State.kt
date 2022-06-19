package com.bhushan.movieapp.data.model

sealed class State<Any> {

    class Loading<Any> : State<Any>()

    data class Success<Any>(val data: Any) : State<Any>()

    data class Error<Any>(val message : String) : State<Any>()

    companion object {

        fun <Any> loading() = Loading<Any>()

        fun <Any> success(data: Any) = Success(data)

        fun <Any> error(message: String) = Error<Any>(message)
    }
}