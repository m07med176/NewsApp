package com.biteam.compose.features.article

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.biteam.compose.utils.ViewModelMVI
import iti.workshop.domain.usecases.news.CheckFavoriteExistUseCase
import iti.workshop.domain.usecases.news.DeleteFavoriteUseCase
import iti.workshop.domain.usecases.news.GetFavoritesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class ArticleViewModel(
    private val checkFavoriteExistUseCase: CheckFavoriteExistUseCase,
    private val deleteFavoriteUseCase: DeleteFavoriteUseCase
): ViewModelMVI<ArticleViewModel.ArticleEventUI>() {
    private val TAG =this::class.java.name

    sealed interface ArticleEventUI{
        object RequestRandomUser:ArticleEventUI
    }

    private val exception = CoroutineExceptionHandler{ _, throwable->
        Log.d(TAG, "Error Happened: ${throwable.message}")
    }

    override fun onEvent(event: ArticleEventUI) {
        when(event){
            is ArticleEventUI.RequestRandomUser -> {
                viewModelScope.launch(exception){

                }
            }
        }
    }
}