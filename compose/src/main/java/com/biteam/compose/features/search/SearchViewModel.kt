package com.biteam.compose.features.search

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.biteam.compose.features.article.ArticleEventUI
import com.biteam.compose.utils.ViewModelMVI
import iti.workshop.data.source.dto.Article
import iti.workshop.domain.usecases.news.QuerySearchUseCase
import iti.workshop.domain.utils.NetworkResponseState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel: ViewModelMVI<ArticleEventUI>() {
    private val TAG =this::class.java.name

    private var article:Article? by mutableStateOf(null)

    private val exception = CoroutineExceptionHandler{ _, throwable->
        Log.d(TAG, "Error Happened: ${throwable.message}")
    }

    override fun onEvent(event: ArticleEventUI) {
        when(event){
            is ArticleEventUI.RequestArticle -> {
                viewModelScope.launch(exception){

                }
            }
        }
    }
}