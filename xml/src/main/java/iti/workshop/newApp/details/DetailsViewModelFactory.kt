package iti.workshop.newApp.details


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iti.workshop.domain.usecases.news.CheckFavoriteExistUseCase
import iti.workshop.domain.usecases.news.DeleteFavoriteUseCase
import iti.workshop.domain.usecases.news.InsertFavoriteUseCase

class DetailsViewModelFactory(
    private val insert: InsertFavoriteUseCase,
    private val delete: DeleteFavoriteUseCase,
    private val checkIsExist: CheckFavoriteExistUseCase
    ): ViewModelProvider.Factory{
    override fun <T:ViewModel> create(modelClass: Class<T>):T{
        if(modelClass.isAssignableFrom(DetailsViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return DetailsViewModel(insert, delete, checkIsExist) as T
        }
        throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
    }

}
