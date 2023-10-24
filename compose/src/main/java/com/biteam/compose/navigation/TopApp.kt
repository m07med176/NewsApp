package com.biteam.compose.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.biteam.compose.R

@Composable
fun TopApp(onClickDrawer:()->Unit,onClickMode:()->Unit) {
    Column{
        Row(
            modifier = Modifier.fillMaxWidth().background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = onClickDrawer) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    tint = Color.Black,
                    contentDescription = null
                )
            }



            Text(
                text = "OUTSIDER",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                    textAlign = TextAlign.Center,
                    letterSpacing = 2.4.sp,
                )
            )

            IconButton(onClick = onClickDrawer) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_night),
                    contentDescription = null,
                    tint = Color.Black
                )
            }

        }

        Spacer(modifier = Modifier.height(22.dp))
        Box(
            modifier = Modifier
                .border(width = 1.dp, color = Color(0xFFEDEDED))
                .fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopAppPreview() {
    TopApp(onClickDrawer = {

    }, onClickMode = {

    })
}