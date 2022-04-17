package com.example.rickandmorty.presenter

import com.example.rickandmorty.interfaces.ApiCharacters
import com.example.rickandmorty.model.CharacterModel
import com.example.rickandmorty.view.CharacterView
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class CharacterPresenter(private val characterView: CharacterView) {

    fun getCharacterFromApi(page: Int) {

        ApiCharacters.api().getCharacters(page).enqueue(object : Callback<CharacterModel> {
            override fun onResponse(
                call: Call<CharacterModel>,
                response: Response<CharacterModel>
            ) {
                characterView.dataFromApi(response)
            }

            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                characterView.errorFromApi(t)
            }
        })
    }
}