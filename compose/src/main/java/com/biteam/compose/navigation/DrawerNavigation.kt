package com.biteam.compose.navigation

import android.service.autofill.OnClickAction
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerComponent(navController: NavController? = null,drawerState: DrawerState?=null) {
    Column(modifier = Modifier
        .width(300.dp)
        .fillMaxHeight()
        .background(Color.White)) {
        DrawerHeader()
        DrawerItems(navController,drawerState)
    }
}

@Composable
fun DrawerHeader() {

    Text(
        text = "News App",
        style = TextStyle(
            fontSize = 20.sp,
            lineHeight = 30.sp,
            fontWeight = FontWeight(800),
            color = Color(0xFF096FFA),
            letterSpacing = 2.4.sp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp),
        textAlign = TextAlign.Center
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerItems(navController: NavController?=null,drawerState: DrawerState?=null) {

    val selectType:NavigationGraph by remember { mutableStateOf(NavigationGraph.CategoryScreen) }

    val coroutineScope = rememberCoroutineScope()
    Spacer(modifier = Modifier.height(15.dp))
    
    navigationDrawer.forEach{
        DrawerItem(model = it,selectType){route->
            navController?.navigate(route.route)
            coroutineScope.launch { drawerState?.close() }
        }
    }
}

@Composable
fun DrawerItem(model:DrawerData,selectType:NavigationGraph,onClickAction: (NavigationGraph)->Unit) {
    Card(
        modifier = Modifier
            .clickable { onClickAction(model.route) }
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 10.dp, vertical = 5.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (model.route == selectType) Color(0xFF096FFA) else Color.LightGray,
            contentColor = if (model.route == selectType) Color.White else Color.Black,
        ),
    ) {
        
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Start, modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(painter = painterResource(id = model.iconId), contentDescription = null,modifier = Modifier.size(25.dp))
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = stringResource(id = model.title),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight(800),
                )
            )
        }
    }
}