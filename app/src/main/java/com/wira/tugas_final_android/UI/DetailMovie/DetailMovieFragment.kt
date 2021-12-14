package com.wira.tugas_final_android.UI.DetailMovie

import android.content.Intent
import android.net.Uri
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

        toolbarDetail.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }

        val name = selectedMovie!!._embedded.show.name + " ~ " + selectedMovie!!.name
        val summary = selectedMovie!!._embedded.show.summary
        val premiered = "Premiered : "+selectedMovie!!._embedded.show.premiered
        val season = "Season : "+selectedMovie!!.season
        val rating = "Rating : "+ selectedMovie!!.rating.average

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

        detailTitle.text = name

        if(summary != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                detailSummary.text = Html.fromHtml(summary, Html.FROM_HTML_MODE_LEGACY)
            } else {
                detailSummary.text = Html.fromHtml(summary)
            }
        }

        detailPremiered.text = premiered
        detailSeason.text = season
        detailRating.text = rating

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

        val tipe = "Type : "+selectedMovie!!.type
        val status = "Status : "+selectedMovie!!._embedded.show.status
        val time = "Time : "+selectedMovie!!._embedded.show.schedule.time

        detailType.text = tipe
        detailStatus.text = status
        detailTime.text = time

        var days = ""
        var dayss = selectedMovie!!._embedded.show.schedule.days
        for((i, day) in dayss.withIndex()) {
            val t = ""+day
            days += if(i != (dayss.size-1)) {
                "$t, "
            } else {
                "$t"
            }
        }
        detailDay.text = "Day : $days"

        shareBtn.setOnClickListener {
            val text = "$name\n\n" +
                    "$summary\n\n" +
                    "Show Info\n" +
                    "----------------------------------\n" +
                    "$premiered\n" +
                    "$season\n" +
                    "$rating\n" +
                    "Genre : $genre\n" +
                    "$tipe\n" +
                    "$status\n" +
                    "Schedule\n" +
                    "\tTime : $time\n" +
                    "\tDay  : $days\n" +
                    "Link : ${selectedMovie!!.url}"

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(
                    Intent.EXTRA_TEXT, text)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }

        lebihLanjutBtn.setOnClickListener {
            val url = selectedMovie!!.url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(url)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailMovieFragment()
        var selectedMovie: DataMovieItem? = null
    }
}