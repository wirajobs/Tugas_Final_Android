package com.wira.tugas_final_android.UI.DetailMovie

import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import com.wira.tugas_final_android.Model.DataMovieItem
import com.wira.tugas_final_android.R
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.item_movie.view.*

class DetailMovieFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(selectedMovie!!._embedded.show.image != null) {
            var urlImg = ""+selectedMovie!!._embedded.show.image?.original
            if(urlImg != "null" || urlImg != "") {
                Picasso.get()
                    .load(urlImg)
                    .into(detailThumb)
            } else {
                urlImg = ""+selectedMovie!!._embedded.show.image?.medium
                if(urlImg != "null" || urlImg != "") {
                    Picasso.get()
                        .load(urlImg)
                        .into(detailThumb)
                }
            }
        }

        detailTitle.text = selectedMovie!!._embedded.show.name

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            detailSummary.text = Html.fromHtml(selectedMovie!!._embedded.show.summary, Html.FROM_HTML_MODE_LEGACY)
        } else {
            detailSummary.text = Html.fromHtml(selectedMovie!!._embedded.show.summary)
        }

        detailPremiered.text = "Premiered : "+selectedMovie!!._embedded.show.premiered
        detailSeason.text = "Season : "+selectedMovie!!.season
        detailRating.text = "Rating : "+ selectedMovie!!.rating.average

        var genre = ""
        var genreList = selectedMovie!!._embedded.show.genres
        for((i, gen) in genreList.withIndex()) {
            val t = ""+gen
            genre += if(i != (genreList.size-1)) {
                "$t, "
            } else {
                "$t"
            }
        }
        detailGenre.text = "Genre : $genre"

        detailType.text = "Type : "+selectedMovie!!.type
        detailStatus.text = "Status : "+selectedMovie!!._embedded.show.status
        detailTime.text = "Time : "+selectedMovie!!._embedded.show.schedule.time

        var days = ""
        var dayss = selectedMovie!!._embedded.show.schedule.days
        for((i, day) in dayss.withIndex()) {
            val t = ""+day
            days += if(i != (genreList.size-1)) {
                "$t, "
            } else {
                "$t"
            }
        }
        detailDay.text = "Day : $days"
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailMovieFragment()
        var selectedMovie: DataMovieItem? = null
    }
}