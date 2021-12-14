package com.wira.tugas_final_android.UI.ListMovie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.wira.tugas_final_android.Model.DataMovieItem
import com.wira.tugas_final_android.R
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.Holder>() {

    private var listMovie = mutableListOf<DataMovieItem>()

    inner class Holder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(movieItem: DataMovieItem) {
            with(itemView) {
                Picasso.get()
                    .load(movieItem._embedded.show.image.original)
                    .into(movieThumb)

                movieTitle.text = movieItem._embedded.show.name
                if(movieItem.rating.average != null) {
                    ratingTxt.text = movieItem.rating.average.toString()
                }
            }
        }
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