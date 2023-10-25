package com.biteam.compose.features.headline

import iti.workshop.data.source.dto.Article

data class HeadlineModel(
    val source:String,
    val title:String,
    val image:String,
    val description:String,
)

sealed interface CategoryEventUI{
    object RequestHeadlines:CategoryEventUI
}


fun List<Article>.toHeadline():List<HeadlineModel> = map { HeadlineModel(
    source = it.author ?: "",
    title = it.title ?: "",
    image = it.urlToImage ?: "",
    description = it.description ?: "",
) }