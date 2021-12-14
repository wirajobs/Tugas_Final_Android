package com.wira.tugas_final_android.Model

class DataMovie : ArrayList<DataMovieItem>()

data class DataMovieItem(
    val _embedded: Embedded,
    val _links: LinksX,
    val airdate: String,
    val airstamp: String,
    val airtime: String,
    val id: Int,
    val image: ImageX,
    val name: String,
    val number: Int,
    val rating: RatingX,
    val runtime: Int,
    val season: Int?,
    val summary: String?,
    val type: String,
    val url: String
)

data class Embedded(
    val show: Show
)

data class LinksX(
    val self: SelfX
)

data class ImageX(
    val medium: String,
    val original: String
)

data class RatingX(
    val average: Any?
)

data class Show(
    val _links: Links,
    val averageRuntime: Int,
    val dvdCountry: Any,
    val ended: Any,
    val externals: Externals,
    val genres: List<Any>,
    val id: Int,
    val image: Image?,
    val language: String,
    val name: String,
    val network: Network,
    val officialSite: String,
    val premiered: String,
    val rating: Rating,
    val runtime: Int,
    val schedule: Schedule,
    val status: String,
    val summary: String?,
    val type: String,
    val updated: Int,
    val url: String,
    val webChannel: Any,
    val weight: Int
)

data class Links(
    val nextepisode: Nextepisode,
    val previousepisode: Previousepisode,
    val self: Self
)

data class Externals(
    val imdb: Any,
    val thetvdb: Any,
    val tvrage: Any
)

data class Image(
    val medium: String?,
    val original: String?
)

data class Network(
    val country: Country,
    val id: Int,
    val name: String
)

data class Rating(
    val average: Any
)

data class Schedule(
    val days: List<String>,
    val time: String
)

data class Nextepisode(
    val href: String
)

data class Previousepisode(
    val href: String
)

data class Self(
    val href: String
)

data class Country(
    val code: String,
    val name: String,
    val timezone: String
)

data class SelfX(
    val href: String
)