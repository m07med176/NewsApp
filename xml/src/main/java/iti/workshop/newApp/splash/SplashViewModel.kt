package iti.workshop.newApp.splash

import android.content.Context
import androidx.annotation.IdRes
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import iti.workshop.data.source.shared.SharedManager
import iti.workshop.newApp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashViewModel(private val _sharedManager:SharedManager):ViewModel() {

    fun seamlessDetector(callback:(isLogged:Boolean)->Unit){
        viewModelScope.launch(Dispatchers.Main) {
            delay(3000)
            callback(_sharedManager.isUserSavedLogging())
        }
    }
}