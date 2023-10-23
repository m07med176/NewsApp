package iti.workshop.newApp.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import iti.workshop.data.source.remote.AuthRemoteDataSource
import iti.workshop.data.source.remote.models.regieter.RegisterBody
import iti.workshop.data.source.remote.retrofit.RetrofitInstance
import iti.workshop.data.repository.news.authentication.AuthRepository
import iti.workshop.domain.usecases.authentication.RegisterUseCase
import iti.workshop.newApp.di.AppDependency
import iti.workshop.newApp.states.RegisterState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.lang.Exception

class RegistrationViewModel(private val getReg: RegisterUseCase):ViewModel() {
    private val _regbool: MutableStateFlow<RegisterState> = MutableStateFlow(RegisterState.Loading)
    val regbool: StateFlow<RegisterState> = _regbool
    fun regUser(body: RegisterBody){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _regbool.value=RegisterState.Success(getReg(body))
            }catch (e: Exception){
                _regbool.value= RegisterState.Failure(e)
            }
        }
    }
//    class RegistrationViewModelFactory(private val getReg:RegisterUseCase,private val body: iti.workshop.data.source.remote.models.regieter.RegisterBody): ViewModelProvider.Factory{
//        override fun <T:ViewModel> create(modelClass: Class<T>):T{
//            if(modelClass.isAssignableFrom(RegistrationViewModel::class.java)){
//                @Suppress("UNCHECKED_CAST")
//                return RegistrationViewModel(getReg,body) as T
//            }
//            throw IllegalArgumentException("UNKNOWN VIEW MODEL CLASS")
//        }
//
//    }
companion object {
    val Factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {

            RegistrationViewModel(
                getReg = AppDependency.registerUseCase
            )
        }
    }
}


}