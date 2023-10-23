package iti.workshop.newApp.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import iti.workshop.data.source.dto.Article
import iti.workshop.domain.usecases.news.CheckFavoriteExistUseCase
import iti.workshop.domain.usecases.news.DeleteFavoriteUseCase
import iti.workshop.domain.usecases.news.InsertFavoriteUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DetailsViewModel(
    val insert: InsertFavoriteUseCase,
    val delete: DeleteFavoriteUseCase,
    val checkIsExist: CheckFavoriteExistUseCase
)
    :ViewModel() {

    private val _isExist =  MutableStateFlow<Boolean>(false)
    val isExist: StateFlow<Boolean>
    get() = _isExist

    fun insertArticle(article: Article){
        viewModelScope.launch {
            insert.invoke(article)
        }
    }

    fun removeArticle(article: Article){
        viewModelScope.launch {
            delete.invoke(article)
        }
    }
    fun checkIfFavoriteExist(title:String?){
        viewModelScope.launch {
            title?.let {
                checkIsExist.invoke(it).catch {
                    _isExist.value = false
                }.collect{
                    _isExist.value = it
                }
            }



        }
    }
}