package iti.workshop.newApp

import android.app.Application
import iti.workshop.newApp.di.AppDependency

class APP: Application() {
    lateinit var  appDependency: Unit

    override fun onCreate() {
        super.onCreate()
        appDependency = AppDependency.initialization(applicationContext)
    }

}