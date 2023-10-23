package com.biteam.compose.features.article

import android.graphics.fonts.FontFamily
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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


@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun CategoryScreen() {
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

    Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth()
        .height(1.dp)
        .background(color = Color(0xFFEDEDED))


}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun CategoryScreenPreview() {

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


}

data class CategoryModel(
    val source:String,
    val title:String,
    val image:String,
    val description:String,
)
@Composable
fun CategoryItem(model:CategoryModel) {
    Column(horizontalAlignment = Alignment.Start) {
        Text(
            text = model.title,
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
                softWrap = true
            )

            Spacer(modifier = Modifier.width(8.dp))


        }
    }
}