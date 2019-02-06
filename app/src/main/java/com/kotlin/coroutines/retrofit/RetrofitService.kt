package com.kotlin.coroutines.retrofit

import kotlinx.coroutines.Deferred
import okhttp3.Response
import retrofit2.http.GET

interface RetrofitService {
    @GET("/todos/1")
    fun getUserDetails(): Deferred<User>
}