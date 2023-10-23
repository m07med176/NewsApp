package iti.workshop.newApp.states

import iti.workshop.data.source.dto.Article


sealed class NewsState{
    class Success(val data:List<Article>):NewsState()
    class Failure(val error:Throwable):NewsState()
    object Loading : NewsState()
}
