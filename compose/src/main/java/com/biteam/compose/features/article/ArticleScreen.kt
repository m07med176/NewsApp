package com.biteam.compose.features.article

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biteam.compose.composable.ImageLoader
import org.koin.androidx.compose.koinViewModel


@Composable
fun TopImageViewer(image: String) {
    var toggleSave by remember { mutableStateOf(false) }

    Card(modifier = Modifier
        .fillMaxWidth()
        .height(270.dp)
        .shadow(5.dp),
        shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp)

    ){
        Box(modifier = Modifier.fillMaxSize()){
            ImageLoader(url = image,modifier = Modifier.fillMaxSize()) {

            }

            Column(horizontalAlignment = Alignment.End,modifier = Modifier.fillMaxSize()) {
                Spacer(modifier = Modifier.fillMaxHeight(0.9f))

                Box(
                    modifier = Modifier
                        .clickable {
                            toggleSave = !toggleSave
                        }
                        .width(104.dp)
                        .height(50.dp)
                        .background(
                            color = Color(0xFFFFFFFF),
                            shape = RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = if(toggleSave) "DONE" else "INSIDER",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(800),
                            color = if(toggleSave) Color.Green else Color(0xFF096FFA),
                        )
                    )
                }
            }
        }
    }

}


@Composable
fun ArticleScreen(navController: NavController?=null,viewModel: ArticleViewModel?= koinViewModel(),model:ArticleData) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {

        TopImageViewer(image = model.image)

        Spacer(modifier = Modifier.height(16.dp))

        Column(modifier = Modifier.padding(horizontal = 21.dp)){
            Text(
                text = model.title,
                style = TextStyle(
                    fontSize = 20.sp,
                    lineHeight = 28.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text(
                    text = model.author,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = model.timestamp,
                    style = TextStyle(
                        fontSize = 14.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF6D787A),
                    )
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = model.description,
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF000000),
                )
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ArticleScreenPreview() {
    val data = ArticleData(
        image = "https://s3-alpha-sig.figma.com/img/4be5/2149/8b032f03da97eeeb81aeddd35d65a2e2?Expires=1699228800&Signature=RRbNcEHE0xCMX9p7CPC9pRmhuuin0L-4Yh9vV0IMqzCpKkjfkHxDJErCgh6Yq4VoCz0OQF9lrh3rPoTrKsBw8ehnh4xVm~LD0GlLX9ZqiHHOog2A2ZwegzzPwzY4F9X2BggCwxWlyMWIfzPyir8grrMW-KlGVhhH1f2jxUpJpl1kcJw~nnnbM8bFVRAYoORXK7JN9KynGz5CQZxgpSUHXiMlegXtI-nMQTk6~W1pok68hZsWJkDCUjFSXKHwVRL~QPAmKWUglodo8Kqu3n9~ODpm48A2qguLzlmcEYLV3qjHfyQBbo5qUx-dQTBjqSrzNjKkWWfYfSLwSFisa72T6Q__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
        title = "Big Tech is making its stuff slower and stupider — on purpose",
        description = "In recent years, Google users have developed one very specific complaint about the ubiquitous search engine: They can't find any answers. A simple search for \"best pc for gaming\" leads to a page dominated by sponsored links rather than helpful advice on which computer to buy. Meanwhile, the actual results are chock-full of low-quality, search-engine-optimized affiliate content designed to generate money for the publisher rather than provide high-quality answers. As a result, users have resorted to work-arounds and hacks to try and find useful information among the ads and low-quality chum. In short, Google's flagship service now sucks.\n" +
                "And Google isn't the only tech giant with a slowly deteriorating core product. Facebook, a website ostensibly for finding and connecting with your friends, constantly floods users' feeds with sponsored (or \"recommended\") content, and seems to bury the things people want to see under what Facebook decides is relevant. And as journalist John Herrman wrote earlier this year, the \"junkification of Amazon\" has made it nearly impossible for users to find a high-quality product they want — instead diverting people to ad-riddled result pages filled with low-quality products from sellers who know how to game the system.",
        timestamp = "Feb 26, 2023, 16.32 PM",
        author = "Ed Zitron"
    )
    ArticleScreen(model = data)
}