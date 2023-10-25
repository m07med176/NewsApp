package iti.workshop.data.source.remote

import iti.workshop.data.source.dto.NewsResponse
import iti.workshop.data.source.remote.retrofit.NewsApi

import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class NewsRemoteDataSource(private  val api:NewsApi):INewsRemoteDataSource {
    override suspend fun getNews(): Response<NewsResponse> = api.getNews()
    override suspend fun queryNews(search: String): Response<NewsResponse> = api.queryNews(search = search)
}