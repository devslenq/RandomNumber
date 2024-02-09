package com.randomnumber.core.data.network

import com.randomnumber.core.data.network.models.ItemNumberModel
import retrofit2.http.GET
import retrofit2.http.Path

@JvmSuppressWildcards
interface Api {

    @GET("{id}")
    fun getInfoAboutNumber(@Path("id") number: Int): retrofit2.Call<ItemNumberModel>

    @GET("random/math")
    fun getRandomInfoAboutNumber(): retrofit2.Call<ItemNumberModel>
}