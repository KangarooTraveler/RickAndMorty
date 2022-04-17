package com.example.rickandmorty.view

import com.example.rickandmorty.model.CharacterModel
import retrofit2.Response

interface CharacterView {
    fun dataFromApi(model: Response<CharacterModel>)
    fun errorFromApi(throwable: Throwable)
}