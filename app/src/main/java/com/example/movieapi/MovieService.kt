package com.example.movieapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
const val BASE_URL = "https://api.themoviedb.org/"
const val API_KEY = "b9d3d371a17bca2f0311ce793c6b23c9"

interface MovieInterface {
    @GET("3/movie/upcoming?api_key=$API_KEY")
    fun getTitle(@Query("language")language : String, @Query("page")page: Int): Call<Movies>
}
object MovieService{
    val newInstances:MovieInterface
    init {
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            GsonConverterFactory.create())
            .build()
        newInstances = retrofit.create(MovieInterface :: class.java)
    }

}
