package iti.workshop.data.source.remote


import iti.workshop.data.source.Constants
import iti.workshop.data.source.dto.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface INewsRemoteDataSource {
    suspend fun getNews(): Response<NewsResponse>
    suspend fun queryNews(search:String,
    ): Response<NewsResponse>
}