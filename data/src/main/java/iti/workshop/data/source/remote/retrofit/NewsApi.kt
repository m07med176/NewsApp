package iti.workshop.data.source.remote.retrofit


import iti.workshop.data.source.Constants
import iti.workshop.data.source.dto.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(Constants.EndPoints.TOP_HEADLINES)
    suspend fun getNews(
        @Query("apiKey") apiKey:String = Constants.NEWS_API_KEY,
        @Query("country") country:String = "us"
    ): Response<NewsResponse>

    @GET(Constants.EndPoints.EVERYTHING)
    suspend fun queryNews(
        @Query("apiKey") apiKey:String = Constants.NEWS_API_KEY,
        @Query("sortBy") sortBy:String = "popularity",
        @Query("q") search:String,
    ): Response<NewsResponse>


}