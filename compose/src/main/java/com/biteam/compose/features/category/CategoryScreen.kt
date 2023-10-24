package com.biteam.compose.features.category

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biteam.compose.composable.ImageLoader


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CategoryScreen(navController: NavController?=null) {
    Column{
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = "TECHNOLOGY",
            style = TextStyle(
                fontSize = 20.sp,
                lineHeight = 30.sp,
                fontWeight = FontWeight(800),
                color = Color(0xFF096FFA),
                letterSpacing = 2.4.sp,
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        Box(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFEDEDED))
        )


        val category = CategoryModel(
            source = "Reuters",
            title = "Big Tech is making its stuff slower and stupider — on purpose",
            image = "https://via.placeholder.com/600/92c952",
            description = "Google, Amazon, and Meta are making their core products worse — on purpose. The user's experience has become subordinate"
        )


        LazyColumn{

            items(listOf(
                category,category,category,category,category,category,category,category,category,category,category,category,category,category,category,category,category,category,category,category
            )){
                CategoryItem(it)
            }
        }
    }

}


@RequiresApi(Build.VERSION_CODES.Q)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryScreenPreview() {
    CategoryScreen()
}



@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryItemPreview() {
    val category = CategoryModel(
        source = "Reuters",
        title = "Big Tech is making its stuff slower and stupider — on purpose",
        image = "https://via.placeholder.com/600/92c952",
        description = "Google, Amazon, and Meta are making their core products worse — on purpose. The user's experience has become subordinate"
    )


    CategoryItem(category)
}

data class CategoryModel(
    val source:String,
    val title:String,
    val image:String,
    val description:String,
)
@Composable
fun CategoryItem(model: CategoryModel) {
    Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(top = 24.dp).padding(horizontal = 20.dp)) {
        Text(
            text = model.source,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(800),
                color = Color(0xFF096FFA),

                )
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row {

            Text(
                text = model.title,
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                softWrap = true,
                modifier = Modifier.fillMaxWidth(0.5f)
            )

            Spacer(modifier = Modifier.width(8.dp))


            ImageLoader(modifier = Modifier.fillMaxWidth(0.5f), url = model.image){

            }

        }

        Spacer(modifier = Modifier.height(20.dp))


        Text(
            text = "Google, Amazon, and Meta are making their core products worse — on purpose. The user's experience has become subordinate",

            style = TextStyle(
                fontSize = 14.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight(400),
                color = Color(0xFF000000),
            )
        )
    }
}