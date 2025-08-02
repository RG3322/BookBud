package com.fire.bookbuddy.presentation.HomeScreen

import android.R.attr.onClick
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.filled.Info
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
import com.fire.bookbuddy.R

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen (navHostController: NavHostController){

    val drawerState: DrawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    val urlHandler = LocalUriHandler.current
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())




    ModalNavigationDrawer(
        drawerState=drawerState ,
        gesturesEnabled = true,
            drawerContent = {
                ModalDrawerSheet {
                    Column (
                        modifier = Modifier.fillMaxSize()
                            .width(250.dp)
                            .padding(16.dp)
                    ){



                        Spacer(modifier = Modifier.height(16.dp))
                        // The Divider composable is designed to draw a thin horizontal line.
                        // This is its default behavior and the reason why you are seeing a line.
                        // If you want to remove the line, you should remove the Divider() composable.
                        Divider()
                        NavigationDrawerItem(label = { Text("Home") },
                            selected = false,
                            onClick = {

                                coroutineScope.launch { drawerState.close() }

                            })
                        Divider()
                        NavigationDrawerItem(label = { Text("Version 1.0") },
                            selected = false,
                            icon = { Icon(imageVector = androidx.compose.material.icons.Icons.Filled.Info, contentDescription = "Version")},
                            onClick = {
                                coroutineScope.launch { drawerState.close() }
                                Toast.makeText(context, "Version 1.0", Toast.LENGTH_SHORT).show()
                            }
                        )
                        Divider()
                        NavigationDrawerItem(label = { Text("Contact me ") },
                            selected = false,
                            icon = { Icon(imageVector = androidx.compose.material.icons.Icons.Filled.Info, contentDescription = "Github")},
                            onClick = {
                                coroutineScope.launch { drawerState.close() }
                                Toast.makeText(context, "Version 1.0", Toast.LENGTH_SHORT).show()
                            }
                        )


                    }
                }

            }
    ){

    }

}