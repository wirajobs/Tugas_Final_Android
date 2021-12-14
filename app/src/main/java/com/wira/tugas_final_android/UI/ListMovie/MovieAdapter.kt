package com.wira.tugas_final_android.UI.ListMovie

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wira.tugas_final_android.Model.DataMovieItem
import com.wira.tugas_final_android.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.Holder>() {
    private val TAG = "MovieAdapter"
    private var listMovie = mutableListOf<DataMovieItem>()

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: DataMovieItem) {
            with(itemView) {

                if(movieItem._embedded.show.image != null) {
                    Log.d(TAG, "bind: ${movieItem._embedded.show.image}")
                    var urlImg = ""+movieItem._embedded.show.image.original
                    if(urlImg != "null" || urlImg != "") {
                        Picasso.get()
                            .load(urlImg)
                            .into(movieThumb)
                    } else {
                        urlImg = ""+movieItem._embedded.show.image.medium
                        if(urlImg != "null" || urlImg != "") {
                            Picasso.get()
                                .load(urlImg)
                                .into(movieThumb)
                        }
                    }
                }

                moviePremiered.text = "Premiered : "+movieItem._embedded.show.premiered

                if(movieItem.season != null) {
                    movieSeason.text = "Season : ${movieItem.season}"
                }

                movieTitle.text = movieItem._embedded.show.name
                if(movieItem.rating.average != null) {
                    ratingTxt.text = movieItem.rating.average.toString()
                }

                var genre = ""
                var genreList = movieItem._embedded.show.genres
                for((i, gen) in genreList.withIndex()) {
                    val t = ""+gen
                    genre += if(i != (genreList.size-1)) {
                        "$t, "
                    } else {
                        "$t"
                    }
                }
                movieGenre.text = "Genre : $genre"
            }
        }
    }

    fun setData(list: MutableList<DataMovieItem>) {
        this.listMovie = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int {
        return listMovie.size
    }


}