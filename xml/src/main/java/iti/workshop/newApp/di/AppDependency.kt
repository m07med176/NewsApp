package iti.workshop.newApp.di

import android.content.Context

import iti.workshop.data.repository.news.NewsRepository
import iti.workshop.data.repository.news.authentication.AuthRepository
import iti.workshop.data.source.local.LocalDataSource
import iti.workshop.data.source.local.room.RoomDB
import iti.workshop.data.source.remote.AuthRemoteDataSource
import iti.workshop.data.source.remote.NewsRemoteDataSource


import iti.workshop.data.source.remote.retrofit.AuthApi

import iti.workshop.data.source.remote.retrofit.NewsApi
import iti.workshop.data.source.remote.retrofit.NewsRetrofitInstance
import iti.workshop.data.source.remote.retrofit.RetrofitInstance
import iti.workshop.data.source.shared.SharedManager
import iti.workshop.domain.usecases.authentication.LoginUseCase
import iti.workshop.domain.usecases.authentication.LogoutUseCase
import iti.workshop.domain.usecases.authentication.RegisterUseCase
import iti.workshop.domain.usecases.news.CheckFavoriteExistUseCase
import iti.workshop.domain.usecases.news.DeleteFavoriteUseCase
import iti.workshop.domain.usecases.news.GetFavoritesUseCase
import iti.workshop.domain.usecases.news.GetNewsUseCase
import iti.workshop.domain.usecases.news.InsertFavoriteUseCase

object AppDependency {

    lateinit var insetFavorite: InsertFavoriteUseCase
    lateinit var getNewsUseCase: GetNewsUseCase
    lateinit var deleteFavoriteUseCase: DeleteFavoriteUseCase
    lateinit var checkFavoriteExistUseCase: CheckFavoriteExistUseCase
    lateinit var getFavoritesUseCase: GetFavoritesUseCase

    lateinit var logoutUseCase: LogoutUseCase
    lateinit var loginUseCase: LoginUseCase
    lateinit var registerUseCase: RegisterUseCase

    fun initialization(appContext: Context) {

        val authApi: AuthApi = RetrofitInstance.authApi
        val newsApi: NewsApi = NewsRetrofitInstance.newsApi
        val remote = NewsRemoteDataSource(newsApi)
        val db = RoomDB.invoke(appContext).articleDao()
        val local = LocalDataSource(db)
        val sharedManager = SharedManager.getInstance(appContext)!!

        val remoteAuth = AuthRemoteDataSource(authApi)
        val authRepo = AuthRepository(sharedManager,remoteAuth)
        val newRepo = NewsRepository(remote, local)

        insetFavorite = InsertFavoriteUseCase(newRepo)
        deleteFavoriteUseCase = DeleteFavoriteUseCase(newRepo)
        getFavoritesUseCase = GetFavoritesUseCase(newRepo)
        checkFavoriteExistUseCase = CheckFavoriteExistUseCase(newRepo)
        getNewsUseCase = GetNewsUseCase(newRepo)

        registerUseCase = RegisterUseCase(authRepo)
        loginUseCase = LoginUseCase(authRepo)
        logoutUseCase = LogoutUseCase(authRepo)
    }


}