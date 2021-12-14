package com.wira.tugas_final_android.UI.ListMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.wira.tugas_final_android.R
import kotlinx.android.synthetic.main.fragment_list_movie.*
import kotlinx.coroutines.launch

class ListMovieFragment : Fragment() {

    private lateinit var viewModel: ListMovieViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[ListMovieViewModel::class.java]
        return inflater.inflate(R.layout.fragment_list_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = MovieAdapter()

        rcView_list_movie.setHasFixedSize(true)
        rcView_list_movie.layoutManager = LinearLayoutManager(requireContext())
        rcView_list_movie.adapter = adapter

        lifecycleScope.launch {
            viewModel.getMovies(adapter)
        }

    }

    companion object {

        @JvmStatic
        fun newInstance() = ListMovieFragment()
    }
}