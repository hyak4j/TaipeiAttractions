package com.hyak4j.cb.tarvel.taipei.model.attractions
data class Attractions(
    val total: Int,
    val `data`: List<Attraction>
)

data class Attraction(
    val id: Int,
    val name: String,
    val name_zh: Any,
    val open_status: Int,
    val introduction: String,
    val open_time: String,
    val zipcode: String,
    val distric: String,
    val address: String,
    val tel: String,
    val fax: String,
    val email: String,
    val months: String,
    val nlat: Double,
    val elong: Double,
    val official_site: String,
    val facebook: String,
    val ticket: String,
    val remind: String,
    val staytime: String,
    val modified: String,
    val url: String,
    val category: List<Category>,
    val target: List<Target>,
    val service: List<Service>,
    val friendly: List<Friendly>,
    val images: List<Image>,
    val files: List<Any>,
    val links: List<AttractionLink>
)

data class Category(
    val id: Int,
    val name: String
)

data class Target(
    val id: Int,
    val name: String
)

data class Service(
    val id: Int,
    val name: String
)

data class Friendly(
    val id: Int,
    val name: String
)

data class Image(
    val src: String,
    val subject: String,
    val ext: String
)

data class AttractionLink(
    val src: String,
    val subject: String
)