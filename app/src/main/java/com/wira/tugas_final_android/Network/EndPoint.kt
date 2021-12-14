package com.wira.tugas_final_android.Network

import com.wira.tugas_final_android.Model.DataMovie
import retrofit2.Response
import retrofit2.http.GET

interface EndPoint {

    @GET("/schedule/full")
    suspend fun getMovies(): Response<DataMovie>

}