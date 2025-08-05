package com.fire.bookbuddy.presentation.HomeScreen

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import com.fire.bookbuddy.R
import com.fire.bookbuddy.presentation.TabScreen.TabScreen
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (navHostController: NavHostController) {

    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val urlHandler = LocalUriHandler.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())




    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = true,
        drawerContent = {
            ModalDrawerSheet {
                Column(
                    modifier = Modifier.fillMaxSize()
                        .width(250.dp)
                        .padding(16.dp)
                ) {


                    Spacer(modifier = Modifier.height(16.dp))
                    // The Divider composable is designed to draw a thin horizontal line.
                    // This is its default behavior and the reason why you are seeing a line.
                    // If you want to remove the line, you should remove the Divider() composable.
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Home") },
                        selected = false,
                        onClick = {

                            coroutineScope.launch { drawerState.close() }

                        })
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Version 1.0") },
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Filled.Info,
                                contentDescription = "Version"
                            )
                        },
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            Toast.makeText(context, "Version 1.0", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Contact me ") },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id =R.drawable.github),
                                contentDescription = "Github",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            coroutineScope.launch { drawerState.close() }
                            Toast.makeText(context, "Version 1.0", Toast.LENGTH_SHORT).show()
                        }
                    )
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Conatct me ") },
                        selected = false,
                        icon = {
                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Filled.Info,
                                contentDescription = "contact me ",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            urlHandler.openUri("//github link//")
                        })
                    Divider()
                    NavigationDrawerItem(
                        label = { Text("Bug Report") },
                        selected = false,
                        icon = {
                            Icon(
                                painter = painterResource(id=R.drawable.bug),
                                contentDescription = "Bugs ",
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        onClick = {
                            urlHandler.openUri("https://github.com/RG3322")
                        })


                }
            }

        }
    ) {


        Scaffold(
            modifier = Modifier.fillMaxSize()
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            topBar = {
                TopAppBar(
                    title = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = "Book Buddy",
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                fontWeight = FontWeight.Bold,
                                fontSize = androidx.compose.material3.MaterialTheme.typography.titleLarge.fontSize
                            )
                        }
                    },
                    navigationIcon = {
                        IconButton(onClick = {
                            coroutineScope.launch { drawerState.open() }
                        }


                        ) {


                            Icon(
                                imageVector = androidx.compose.material.icons.Icons.Filled.Menu,
                                contentDescription = "Open Drawer"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior
                )
            })
        { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                TabScreen(navHostController = navHostController)


            }
        }
    }
}

