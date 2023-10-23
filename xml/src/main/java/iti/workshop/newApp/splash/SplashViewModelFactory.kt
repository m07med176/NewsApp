package iti.workshop.newApp.splash

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import iti.workshop.data.source.shared.SharedManager

class SplashViewModelFactory constructor(private val sharedManager: SharedManager): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            SplashViewModel(this.sharedManager) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}
