package iti.workshop.newApp.favorite

import androidx.lifecycle.ViewModel
import iti.workshop.data.source.dto.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class FavoriteViewModel:ViewModel() {
    private var _allFavourites: MutableStateFlow<List<Article>?> = MutableStateFlow(null)
    val allFavourites: StateFlow<List<Article>?> = _allFavourites
}