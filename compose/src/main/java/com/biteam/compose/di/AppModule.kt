package com.biteam.compose.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideRemoteSource(): IRemoteDataSource {
//        return RemoteDataSource(RetrofitInstance().api)
//    }
//
////    @Provides
////    @Singleton
////    fun provideLocalDB(@ApplicationContext context: Context): ILocalDataSource {
////        val db = RoomDB.invoke(context)
////        return LocalDataSource(db.cashDao())
////    }
//
//    @Provides
//    @Singleton
//    fun provideRepository(
//        remote: IRemoteDataSource
//    ): IRepository {
//        return RepositoryImpl(remote)
//    }
//


}


