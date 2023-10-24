package com.biteam.compose.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.biteam.compose.R

@Composable
fun ImageLoader(modifier:Modifier = Modifier, url:String, onReturnUrl:((String)->Unit)?=null, onClick:(String)->Unit) {

    SubcomposeAsyncImage(
        modifier = modifier.clickable { onClick(url) },
        model =url, contentDescription = null,
        loading = {
            Box(modifier = modifier, contentAlignment =  Alignment.Center){
                CircularProgressIndicator(modifier = Modifier
                    .size(60.dp)
                    .padding(10.dp))
            }
        },
        contentScale = ContentScale.Crop,
        error = {
            Image(painter = painterResource(id = R.drawable.placeholder), contentDescription = null)
        },
        onSuccess = {
            onReturnUrl?.invoke(url)
        }
    )
}