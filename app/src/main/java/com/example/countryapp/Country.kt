package com.example.countryapp

import retrofit2.Call
import retrofit2.http.GET

interface Country {

    @GET("all")
    fun getCountrty() : Call<List<CountryModel>>
}