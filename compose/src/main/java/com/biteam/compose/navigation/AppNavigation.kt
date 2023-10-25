package com.biteam.compose.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.biteam.compose.composable.SlideInOutAnimation
import com.biteam.compose.features.article.ArticleData
import com.biteam.compose.features.article.ArticleScreen
import com.biteam.compose.features.headline.CategoryScreen
import com.biteam.compose.features.search.SearchScreen

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = NavigationGraph.CategoryScreen.route,
    ) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(
            route = NavigationGraph.CategoryScreen.route,
        ) {
            SlideInOutAnimation{ CategoryScreen(navController) }
        }

        composable(
            route = NavigationGraph.SearchScreen.route,
        ) {
            SlideInOutAnimation{ SearchScreen(navController) }
        }

        composable(
            route = NavigationGraph.ArticleScreen.route,
        ) {
            val data = ArticleData(
                image = "https://s3-alpha-sig.figma.com/img/4be5/2149/8b032f03da97eeeb81aeddd35d65a2e2?Expires=1699228800&Signature=RRbNcEHE0xCMX9p7CPC9pRmhuuin0L-4Yh9vV0IMqzCpKkjfkHxDJErCgh6Yq4VoCz0OQF9lrh3rPoTrKsBw8ehnh4xVm~LD0GlLX9ZqiHHOog2A2ZwegzzPwzY4F9X2BggCwxWlyMWIfzPyir8grrMW-KlGVhhH1f2jxUpJpl1kcJw~nnnbM8bFVRAYoORXK7JN9KynGz5CQZxgpSUHXiMlegXtI-nMQTk6~W1pok68hZsWJkDCUjFSXKHwVRL~QPAmKWUglodo8Kqu3n9~ODpm48A2qguLzlmcEYLV3qjHfyQBbo5qUx-dQTBjqSrzNjKkWWfYfSLwSFisa72T6Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
                title = "Big Tech is making its stuff slower and stupider — on purpose",
                description = "In recent years, Google users have developed one very specific complaint about the ubiquitous search engine: They can't find any answers. A simple search for \"best pc for gaming\" leads to a page dominated by sponsored links rather than helpful advice on which computer to buy. Meanwhile, the actual results are chock-full of low-quality, search-engine-optimized affiliate content designed to generate money for the publisher rather than provide high-quality answers. As a result, users have resorted to work-arounds and hacks to try and find useful information among the ads and low-quality chum. In short, Google's flagship service now sucks.\n" +
                        "And Google isn't the only tech giant with a slowly deteriorating core product. Facebook, a website ostensibly for finding and connecting with your friends, constantly floods users' feeds with sponsored (or \"recommended\") content, and seems to bury the things people want to see under what Facebook decides is relevant. And as journalist John Herrman wrote earlier this year, the \"junkification of Amazon\" has made it nearly impossible for users to find a high-quality product they want — instead diverting people to ad-riddled result pages filled with low-quality products from sellers who know how to game the system.",
                timestamp = "Feb 26, 2023, 16.32 PM",
                author = "Ed Zitron"
            )
            SlideInOutAnimation{ ArticleScreen(navController = navController, model =data) }
        }



    }
}

