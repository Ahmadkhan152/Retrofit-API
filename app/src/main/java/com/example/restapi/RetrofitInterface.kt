package com.example.restapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitInterface {

    //@get:GET("api/users?page=0")
    @GET("api/users?")
    fun listRepos(@Query("page") user: String):Call<PostModel?>
    val post:Call<PostModel?>
    companion object{
    const val base_url: String ="https://reqres.in"
    val rf: Retrofit = Retrofit.Builder()
            .baseUrl(RetrofitInterface.base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    //fun listRepos(@Path("user") user: String?)//: Call<PostModel?>?
}