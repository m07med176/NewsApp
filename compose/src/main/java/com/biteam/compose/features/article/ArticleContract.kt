package com.biteam.compose.features.article

import iti.workshop.data.source.dto.Article

data class ArticleData(
    val image:String,
    val title:String,
    val author:String,
    val timestamp:String,
    val description:String
)

sealed interface ArticleEventUI{
    data class RequestArticle(val title:String):ArticleEventUI
}

fun List<Article>.toArticle():List<ArticleData> = map { ArticleData(
    timestamp = it.publishedAt ?: "",
    title = it.title ?: "",
    image = it.urlToImage ?: "",
    description = it.description ?: "",
    author = it.author ?: ""
) }