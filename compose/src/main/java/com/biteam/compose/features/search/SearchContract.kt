package com.biteam.compose.features.search

import iti.workshop.data.source.dto.Article

data class SearchModel(
    val title:String,
    val image:String,
    val description:String,
    val timestamp:String
)


sealed interface SearchEventUI{
    data class RequestSearch(val search:String):SearchEventUI
}


fun List<Article>.toSearch():List<SearchModel> = map { SearchModel(
    timestamp = it.publishedAt ?: "",
    title = it.title ?: "",
    image = it.urlToImage ?: "",
    description = it.description ?: "",
) }