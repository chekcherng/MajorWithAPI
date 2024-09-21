package com.example.yujie.Major

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.yujie.Major.MajorViewModel
import com.example.yujie.movie_module.ComposeBottomBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Promotions(vm: MajorViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Promotions", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        },
        bottomBar = { ComposeMajorBottomBar(navController = navController, vm = vm) },
        content = { padding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .background(Color.Black)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Black.copy(alpha = 0.5f))
                ) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val currentRoute =
                                navController.currentBackStackEntryAsState().value?.destination?.route

                            val favoriteTextColor = if (currentRoute == "Fav") Color.Yellow else Color.White
                            val cinemaTextColor = if (currentRoute == "Major_cinema") Color.Yellow else Color.White

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .clickable { navController.navigate("Major_cinema") },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "CINEMAS",
                                    fontSize = 14.sp,
                                    color = cinemaTextColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .clickable { navController.navigate("Fav") },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "FAVORITE",
                                    fontSize = 14.sp,
                                    color = favoriteTextColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                    // Add more items here if needed
                }
            }
        }
    )
}
