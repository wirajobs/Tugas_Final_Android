package com.wira.tugas_final_android.UI.ListMovie

import android.util.Log
import androidx.lifecycle.ViewModel
import com.wira.tugas_final_android.Model.DataMovie
import com.wira.tugas_final_android.Model.DataMovieItem
import com.wira.tugas_final_android.Network.RetrofitClient
import retrofit2.Response
import java.lang.Exception

class ListMovieViewModel: ViewModel() {
    private val TAG = "ListMovieViewModel"

    private var listMovie = mutableListOf<DataMovieItem>()

    suspend fun getMovies(adapter: MovieAdapter) {
        if(listMovie.isEmpty()) {
            var response: Response<DataMovie>? = null
            try {
                response = RetrofitClient.instance.getMovies()
                if(response.isSuccessful) {
                    adapter.setData(response.body()!!)
                }
            } catch (e: Exception) {
                Log.e(TAG, "${e.message}")
            }
        } else {
            adapter.setData(listMovie)
        }
    }

}