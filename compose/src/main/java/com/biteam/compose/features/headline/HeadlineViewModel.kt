package com.biteam.compose.features.headline

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.biteam.compose.utils.ViewModelMVI
import iti.workshop.data.source.dto.Article
import iti.workshop.domain.usecases.news.GetNewsUseCase
import iti.workshop.domain.utils.NetworkResponseState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HeadlineViewModel(
    private val getNewsUseCase: GetNewsUseCase,

): ViewModelMVI<CategoryEventUI>() {
    private val TAG =this::class.java.name


    private val _stateHeadline: MutableStateFlow<NetworkResponseState<List<Article>?>> = MutableStateFlow(NetworkResponseState.OnLoading())
    val stateHeadline: StateFlow<NetworkResponseState<List<Article>?>> = _stateHeadline.asStateFlow()

    private val exception = CoroutineExceptionHandler{ _, throwable->
        Log.d(TAG, "Error Happened: ${throwable.message}")
    }

    override fun onEvent(event: CategoryEventUI) {
        when(event){
            is CategoryEventUI.RequestHeadlines -> {
                viewModelScope.launch(exception){
                    getNewsUseCase.invoke().collect{
                        _stateHeadline.value = it
                    }
                }
            }
        }
    }
}