import com.biteam.compose.features.article.ArticleViewModel
import com.biteam.compose.features.headline.HeadlineViewModel
import com.biteam.compose.features.search.SearchViewModel
import iti.workshop.data.repository.news.NewsRepository
import iti.workshop.data.repository.news.NewsRepositoryInterface
import iti.workshop.data.source.local.ILocalDataSource
import iti.workshop.data.source.local.LocalDataSource
import iti.workshop.data.source.local.room.ArticlesDao
import iti.workshop.data.source.local.room.RoomDB
import iti.workshop.data.source.remote.INewsRemoteDataSource
import iti.workshop.data.source.remote.NewsRemoteDataSource
import iti.workshop.data.source.remote.retrofit.NewsRetrofitInstance
import iti.workshop.domain.usecases.news.CheckFavoriteExistUseCase
import iti.workshop.domain.usecases.news.DeleteFavoriteUseCase
import iti.workshop.domain.usecases.news.GetFavoritesUseCase
import iti.workshop.domain.usecases.news.GetNewsUseCase
import iti.workshop.domain.usecases.news.InsertFavoriteUseCase
import iti.workshop.domain.usecases.news.QuerySearchUseCase
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {

    // Data Layer
    single<ArticlesDao> { RoomDB.invoke(androidContext()).articleDao() }
    single<ILocalDataSource> { LocalDataSource(get()) }

    single<INewsRemoteDataSource> { NewsRemoteDataSource(NewsRetrofitInstance.newsApi ) }

    single<NewsRepositoryInterface> { NewsRepository(get(),get()) }

    // Domain Layer (use cases)
    single { GetNewsUseCase(get()) }
    single { QuerySearchUseCase(get()) }
    single { GetFavoritesUseCase(get()) }
    single { DeleteFavoriteUseCase(get()) }
    single { InsertFavoriteUseCase(get()) }
    single { CheckFavoriteExistUseCase(get()) }

    // Presentation Layer (View Model)
    viewModelOf(::SearchViewModel)
    viewModelOf(::HeadlineViewModel)
    viewModelOf(::ArticleViewModel)
}



