package com.biteam.compose.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.biteam.compose.R

sealed class NavigationGraph(val route:String){
    object CategoryScreen: NavigationGraph(route = "category_screen")
    object ArticleScreen: NavigationGraph(route = "article_screen")
    object SearchScreen: NavigationGraph(route = "search_screen")
}

data class DrawerData(@StringRes val title:Int,@DrawableRes val iconId:Int,val route: NavigationGraph)

val navigationDrawer = listOf(
    DrawerData(title = R.string.drawer_category, iconId = R.drawable.ic_category,route = NavigationGraph.CategoryScreen),
    DrawerData(title = R.string.drawer_article, iconId = R.drawable.ic_article,route = NavigationGraph.ArticleScreen),
    DrawerData(title = R.string.drawer_search, iconId = R.drawable.ic_search,route = NavigationGraph.SearchScreen)
)
