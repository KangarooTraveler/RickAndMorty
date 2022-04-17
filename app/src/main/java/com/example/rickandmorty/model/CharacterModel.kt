package com.example.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterModel(
    @SerializedName("results")
    val results: List<Results>,
)

data class Results(
    @SerializedName("name")
    val name: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("gender")
    val gender: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("episode")
    val episode: List<String>,
)

data class Location(
    @SerializedName("name")
    val locationName: String,
)