package com.fire.bookbuddy.presentation.TabScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Category
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.material.tabs.TabItem
import com.rizzi.bouquet.dp
import kotlinx.coroutines.launch

@Composable
fun TabScreen(navHostController: NavHostController) {
 val tabs = listOf(
     TabItem("Category", Icons.Default.Category),
     TabItem("Books", Icons.Default.Book))

    val pagerState = rememberPagerState(pageCount = { tabs.size })
    val scope = rememberCoroutineScope()

    Column(modifier = Modifier.fillMaxSize()) {
        TabRow( selectedTabIndex = pagerState.currentPage,
                modifier= Modifier.fillMaxWidth())
        {
           tabs.forEachIndexed { index, tabItem ->

               Tab(
                   modifier =Modifier.fillMaxWidth(),
                   selected = pagerState.currentPage == index,
                   onClick = {
                       scope.launch {
                           pagerState.animateScrollToPage(index)
                       }
                   },
                   text = {
                       Row (verticalAlignment = Alignment.CenterVertically){
                           Icon(
                               imageVector = tab.icon, contentDescription = tab.title
                               , modifier = Modifier.size(24.dp)
                           )
                           Spacer(modifier = Modifier.size(8.dp))
                           Text(tab.title)
                       }
                   }
               )

           }

        }

    }



}
data class TabItem(val title: String, val icon :ImageVector)