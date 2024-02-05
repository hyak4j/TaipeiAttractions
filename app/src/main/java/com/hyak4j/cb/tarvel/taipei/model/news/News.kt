package com.hyak4j.cb.tarvel.taipei.model.news

data class News(
    val total: Int,
    val `data`: List<Data>
)

data class Data(
    val id: Int,
    val title: String,
    val description: String,
    val begin: Any,
    val end: Any,
    val posted: String,
    val modified: String,
    val url: String,
    val files: List<File>,
    val links: List<NewsLink>
)

data class File(
    val src: String,
    val subject: String,
    val ext: String
)

data class NewsLink(
    val src: String,
    val subject: String
)