package com.ipacsystemtest.rest.api

import com.ipacsystemtest.rest.api.data.UsersList
import retrofit2.http.GET
import retrofit2.http.Query

interface IPACApi {

    companion object {
        const val BASE_URL = "https://randomuser.me/"
    }

    @GET("api/?")
    suspend fun getMoviesList(@Query("results") results: Int): UsersList


}