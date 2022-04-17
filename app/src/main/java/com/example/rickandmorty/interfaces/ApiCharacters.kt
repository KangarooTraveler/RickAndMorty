package com.example.rickandmorty.interfaces

import com.example.rickandmorty.model.CharacterModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiCharacters {

    @GET("/api/character/")
    fun getCharacters(
        @Query("page") page: Int
    ): Call<CharacterModel>

    companion object {
        fun api(): ApiCharacters {
            val retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ApiCharacters::class.java)
        }
    }
}