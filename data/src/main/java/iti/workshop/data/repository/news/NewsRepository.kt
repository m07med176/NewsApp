package iti.workshop.data.repository.news

import iti.workshop.data.source.dto.Article
import iti.workshop.data.source.dto.NewsResponse
import iti.workshop.data.source.local.ILocalDataSource
import iti.workshop.data.source.remote.INewsRemoteDataSource
import iti.workshop.data.source.remote.NewsRemoteDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import retrofit2.Response

class NewsRepository(
    val remote: INewsRemoteDataSource,
    val local: ILocalDataSource,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
    ): NewsRepositoryInterface {
    override suspend fun getNews(): Response<NewsResponse> = remote.getNews()
    override suspend fun queryNews(search: String): Response<NewsResponse> = remote.queryNews(search = search)

    override fun getArticles(): Flow<List<Article>>  = local.getArticles().flowOn(dispatcher)

    override suspend fun insertArticle(article: Article) {
        withContext(dispatcher){
            local.insertArticle(article)
        }
    }

    override suspend fun deleteArticle(article: Article) {
        withContext(dispatcher){
            local.deleteArticle(article)
        }
    }

    override  fun isExists(title: String): Flow<Boolean>  = local.isExists(title).flowOn(dispatcher)

}