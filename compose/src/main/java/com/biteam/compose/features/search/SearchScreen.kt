package com.biteam.compose.features.search

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.biteam.compose.R
import com.biteam.compose.composable.ImageLoader
import com.biteam.compose.composable.LottieStateUI
import com.biteam.compose.composable.StateOfData
import com.biteam.compose.features.headline.CategoryItem
import com.biteam.compose.features.headline.toHeadline
import iti.workshop.data.source.dto.Article
import iti.workshop.domain.utils.NetworkResponseState
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchField(modifier: Modifier = Modifier,onClickSearch:(String)->Unit) {
    var result by remember{ mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current


    TextField(
        modifier = modifier.border(width = 1.dp, color = Color(0xFF8D8D94), shape = RoundedCornerShape(size = 8.dp)),
        value = result,
        onValueChange = {
        result = it
    },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        placeholder = {
            Text(
                text = "Search",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color =Color.Gray,
                )
            )
        },

        trailingIcon = {
            if (result.isNotBlank()){
                Icon(imageVector = Icons.Default.Close, contentDescription = null, tint = Color.Black, modifier = Modifier.clickable {
                    result = ""
                })

            }else{
                Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color.Black)

            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            keyboardController?.hide()
            onClickSearch(result)
        })

    )
}
@Composable
fun SearchScreen(navController: NavController?=null,viewModel: SearchViewModel? = koinViewModel()) {
    val coroutineScope = rememberCoroutineScope()

    val searchState = viewModel?.stateSearch?.collectAsState()



    Column(horizontalAlignment = Alignment.Start){

        Spacer(modifier = Modifier.height(32.dp))

        var querySearch by remember { mutableStateOf("") }

        SearchField(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)){
            querySearch = it
            coroutineScope.launch {
                viewModel?.onEvent(SearchEventUI.RequestSearch(querySearch))
            }
        }

        if (querySearch.isNotBlank()){
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Results for “${querySearch}”",
                style = TextStyle(
                    fontSize = 18.sp,
                    lineHeight = 26.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFF000000),
                ),
                modifier = Modifier.padding(horizontal = 10.dp)
            )
        }

        Spacer(modifier = Modifier.height(22.dp))

        when(searchState?.value){
            is NetworkResponseState.OnError -> {
                LottieStateUI(
                    modifier = Modifier.fillMaxSize(),
                    type = StateOfData.Error,
                    message = (searchState.value as NetworkResponseState.OnError<List<Article>?>).error.message
                )
            }
            is NetworkResponseState.OnNetworkError -> {
                LottieStateUI(
                    modifier = Modifier.fillMaxSize(),
                    type = StateOfData.Error,
                    message = (searchState.value as NetworkResponseState.OnNetworkError<List<Article>?>).data
                )
            }
            is NetworkResponseState.OnLoading -> {
                LottieStateUI(
                    modifier = Modifier.fillMaxSize(),
                    type = StateOfData.Loading,
                    message = stringResource(
                        R.string.still_fetch_data
                    )
                )
            }
            is NetworkResponseState.OnSuccess -> {
                val data = (searchState.value as NetworkResponseState.OnSuccess<List<Article>?>).data?.toSearch() ?: emptyList()
                if (data.isEmpty()){
                    LottieStateUI(
                        modifier = Modifier.fillMaxSize(),
                        type = StateOfData.NoData,
                        message = stringResource(
                            R.string.there_is_no_data
                        )
                    )
                }else{
                    LazyColumn{
                        items(data){
                            SearchItem(it)
                        }
                    }
                }
            }

            is NetworkResponseState.OnIdle -> {
                LottieStateUI(
                    modifier = Modifier.fillMaxSize(),
                    type = StateOfData.Idle,
                    message = stringResource(
                        R.string.start_search
                    )
                )
            }
            null -> {}
        }
    }
}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen()
}

@Preview(showBackground = true)
@Composable
fun SearchItemPreview() {
    val data = SearchModel(
        title = "CNN",
        image = "https://s3-alpha-sig.figma.com/img/0d99/8bb6/b4366e3f47beeadb8b19e6d914e9e43f?Expires=1699228800&Signature=Wmc6YezlzXN4LkqezKwn2ewH97NBaza4KtNsyAGd8E2LgUjwwaSPoNqL1n~2Q2OKt1OvR~SziGqfgueDskk7mf7SUJm7wy8Egs50eryfHBOH1VFku60pgaFGeGma8FlB5OxWklT2rp~YacgjJkPrS0Ds63fpGWbKvey478sPXkGOMuD2mtf7FKYn716is-dsisZDYrSoF2uuhvC9xG1hz6P8Epb0-n2F7JaIGKGRL0C~gPN-SCVnspdkD3lehYUNaqWcAh46U9QyMLONVo1HVycZocp78VWbz5n90g9l9a4j4xFgzs7kq0Oah6~4sUDL2JZBkcGfp9fmziTR5dx1lg__&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4",
        description = "Why TikTok is taking months to delete personal US user data from servers outside its Project Texas firewalls, even as its political standing sours",
        timestamp = "Feb 26, 2023, 16.32 PM"
    )

    SearchItem(data)

}


@Composable
fun SearchItem(model: SearchModel) {

    Column(modifier = Modifier
        .padding(horizontal = 20.dp)
        .padding(bottom = 16.dp)){
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            ImageLoader(modifier = Modifier.fillMaxWidth(0.3f), url = model.image) {

            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {
                Text(
                    text = model.title,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(800),
                        color = Color(0xFF096FFA),
                    )
                )

                Spacer(modifier = Modifier.height(8.dp))


                Text(
                    text = model.description,
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ),
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis,
                    softWrap = true
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = model.timestamp,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight(400),
                        color = Color(0xFF6D787A),
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))


            }


        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(color = Color(0xFFEDEDED))
        )
    }

}