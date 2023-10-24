package iti.workshop.data.source.remote.retrofit

import iti.workshop.data.source.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NewsRetrofitInstance {
    private val retrofitNews: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Constants.NEWS_URL)
            .client(cashAndLoggerManager())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    val newsApi: NewsApi by  lazy {
        retrofitNews.create(NewsApi::class.java)
    }


    private fun cashAndLoggerManager(): OkHttpClient {
        // Logging Retrofit
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }
}