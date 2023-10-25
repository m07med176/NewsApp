package iti.workshop.domain.usecases.news

import iti.workshop.data.repository.news.NewsRepositoryInterface
import iti.workshop.data.source.dto.Article
import iti.workshop.domain.utils.NetworkResponseState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetNewsUseCase(
    private val repository: NewsRepositoryInterface,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    operator fun invoke(): Flow<NetworkResponseState<List<Article>?>> = flow {
        val response = repository.getNews()
        if (response.isSuccessful) {
            emit(NetworkResponseState.OnSuccess(repository.getNews().body()?.articles))
        } else {
            emit(
                NetworkResponseState.OnNetworkError(
                    code = response.code(),
                    data = response.errorBody().toString()
                )
            )
        }
    }.catch { emit(NetworkResponseState.OnError(it)) }.flowOn(dispatcher)
}
