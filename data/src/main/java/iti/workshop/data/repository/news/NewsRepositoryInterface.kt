package iti.workshop.data.repository.news



import iti.workshop.data.source.dto.Article
import iti.workshop.data.source.dto.NewsResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NewsRepositoryInterface {
    suspend fun getNews(): Response<NewsResponse>

    suspend fun queryNews(search:String): Response<NewsResponse>
    fun getArticles(): Flow<List<Article>>
    suspend fun insertArticle(article: Article)
    suspend fun deleteArticle(article: Article)
    fun isExists(title: String): Flow<Boolean>
}