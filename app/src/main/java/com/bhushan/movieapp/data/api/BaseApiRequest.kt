package com.bhushan.movieapp.data.api

import retrofit2.Response

abstract class BaseApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful && response.body() != null) {
            return response.body()!!
        } else {
            throw Exception(response.code().toString())
        }
    }
}