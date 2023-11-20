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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class SearchViewModel(private val searchQuery:QuerySearchUseCase): ViewModelMVI<SearchEventUI>() {
    private val TAG =this::class.java.name

    private var _stateSearch:MutableSharedFlow<NetworkResponseState<List<Article>?>>  = MutableSharedFlow()
    var stateSearch = _stateSearch.asSharedFlow()

     override fun onEvent(event: SearchEventUI) {
        when(event){
            is SearchEventUI.RequestSearch -> {
                searchQuery(event.search)
                    .catch {
                        _stateSearch.emit(NetworkResponseState.OnError(it))
                    }
                    .onEach {
                        _stateSearch.emit(it)
                }.launchIn(viewModelScope)
            }
        }
    }
}