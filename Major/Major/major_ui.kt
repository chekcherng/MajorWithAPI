package com.example.yujie.Major

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.CardGiftcard
import androidx.compose.material.icons.rounded.LocalMovies
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MajorScaffold(vm: MajorViewModel, navController: NavController) {
    Scaffold { paddingValues ->
        Surface(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController as NavHostController, startDestination = "home") {
                composable("home") {
                    MajorBody(vm, navController)
                }
                composable("Major_cinema") {
                    MajorCinema(vm, navController = navController)
                }
                composable("detail/{id}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")
                    id?.let {
                        DetailPageMajor(mid = it, vm = vm, navController = navController)
                    }
                }
                composable("Coming") {
                    Coming(vm, navController)
                }
                composable("Fav") {
                    FavCinema(vm, navController = navController)
                }
                composable("User") {
                    UserMajorpage(navController = navController)
                }
                composable("Setting") {
                    MajorSetting( vm,navController)
                }
                composable("Promotion") {
                    Promotions( vm,navController)
                }
                composable("User") {
                    UserMajorpage(navController = navController)
                }
                composable("search") {
                    val viewModel: MajorViewModel= viewModel()
                    LaunchedEffect(Unit) {
                        viewModel.getMajorResultList()
                    }
                    SearchMajorPage(
                        navController = navController,
                        viewModel = viewModel,
                        movies = viewModel.majorList
                    )
                }

            }
        }
    }
}



@Composable
fun ComposeMajorBottomBar(navController: NavController, vm: MajorViewModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Icon(
                    Icons.Rounded.LocalMovies,
                    contentDescription = "Home",
                    tint = Color.White
                )
            }
            Text("MOVIES", color = Color.White)
        }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = {
                    navController.navigate("Major_cinema")
                }
            ) {
                Icon(
                    Icons.Rounded.LocationOn,
                    contentDescription = "Major Cinema",
                    tint = Color.White
                )
            }
            Text("CINEMAS", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = {
                    navController.navigate("Promotion")
                }
            ) {
                Icon(
                    Icons.Rounded.CardGiftcard,
                    contentDescription = "Major Cinema",
                    tint = Color.White
                )
            }
            Text("Promotions", color = Color.White)
        }
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            IconButton(
                onClick = {
                    navController.navigate("Setting")
                }
            ) {
                Icon(
                    Icons.Rounded.Settings,
                    contentDescription = "Major Cinema",
                    tint = Color.White
                )
            }
            Text("SETTING", color = Color.White)
        }


    }
}
