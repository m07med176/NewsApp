package iti.workshop.newApp.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import iti.workshop.data.repository.news.NewsRepositoryInterface
import iti.workshop.data.source.dto.Article
import iti.workshop.data.source.remote.models.regieter.RegisterBody
import iti.workshop.domain.usecases.authentication.RegisterUseCase
import iti.workshop.domain.usecases.news.GetNewsUseCase
import iti.workshop.newApp.di.AppDependency
import iti.workshop.newApp.states.NewsState
import iti.workshop.newApp.states.RegisterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsListViewModel(private val getNews: GetNewsUseCase):ViewModel() {
    private val _newsStatus: MutableStateFlow<NewsState> = MutableStateFlow(NewsState.Loading)
    val newsStatus: StateFlow<NewsState> = _newsStatus
   private lateinit var news :List<Article>
    fun updateNews(){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getNews().flowOn(Dispatchers.IO).collect{
                    news=it
                }
                _newsStatus.value= NewsState.Success(news)
            }catch (e: Exception){
               _newsStatus.value= NewsState.Failure(e)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {

                NewsListViewModel(
                    getNews = AppDependency.getNewsUseCase
                )
            }
        }
    }

}