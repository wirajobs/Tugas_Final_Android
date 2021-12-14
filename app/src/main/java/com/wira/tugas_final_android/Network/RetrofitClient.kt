package com.wira.tugas_final_android.Network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    private const val Base_URL = "https://api.tvmaze.com/"

    val instance : EndPoint by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EndPoint::class.java)
    }

}