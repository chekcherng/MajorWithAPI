package com.example.yujie.Major

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.yujie.mo.majorItems

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MajorCinema(vm: MajorViewModel, navController: NavController) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }

    // Filter cinemaItems based on searchQuery
    val filteredCinemaItems by remember(searchQuery) {
        derivedStateOf {
            majorItems.filter {
                it.title.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "CINEMAS", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black),
                actions = {
                    IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            ComposeMajorBottomBar(navController = navController, vm = vm)
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(padding)
            ) {
                // Show TextField if search is visible
                if (isSearchVisible) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search...", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Black,
                            unfocusedContainerColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                // Navigation buttons for Now Showing and Coming Soon
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                    val CinemaTextColor = if (currentRoute == "Major_cinema") Color.Yellow else Color.White
                    val FavoriteTextColor = if (currentRoute == "Fav") Color.Yellow else Color.White


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
                            color = CinemaTextColor,
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
                            color = FavoriteTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    }



                }

                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.background(Color.Black)
                ) {
                    items(filteredCinemaItems) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF292829))
                                .padding(16.dp)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Text(
                                        text = item.title,
                                        fontSize = 16.sp,
                                        color = Color.White,
                                        modifier = Modifier
                                            .weight(1f)
                                            .padding(bottom = 4.dp)
                                    )
                                    Icon(
                                        imageVector = if (vm.favoriteItems.contains(item)) Icons.Filled.Star else Icons.Filled.StarOutline,
                                        contentDescription = "Rating icon",
                                        tint = Color.White,
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .clickable { vm.toggleFavorite(item) } // Toggle favorite on click
                                    )
                                }
                            }
                        }
                    }
                }

            }

        }
    )
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavCinema(vm: MajorViewModel, navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchVisible by remember { mutableStateOf(false) }

    // Filter cinemaItems based on searchQuery
    val filteredCinemaItems by remember(searchQuery) {
        derivedStateOf {
            majorItems.filter {
                it.title.contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "CINEMAS", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black),
                actions = {
                    IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                        Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }
                }
            )
        },
        bottomBar = {
            ComposeMajorBottomBar(navController = navController, vm = vm)
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black)
                    .padding(padding)
            ) {
                // Show TextField if search is visible
                if (isSearchVisible) {
                    TextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text("Search...", color = Color.Gray) },
                        colors = TextFieldDefaults.colors(
                            focusedTextColor = Color.White,
                            unfocusedTextColor = Color.White,
                            focusedContainerColor = Color.Black,
                            unfocusedContainerColor = Color.Black,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            cursorColor = Color.White
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                    )
                }

                // Navigation buttons for Now Showing and Coming Soon
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    val currentRoute =
                        navController.currentBackStackEntryAsState().value?.destination?.route

                    val FavoriteTextColor = if (currentRoute == "Fav") Color.Yellow else Color.White
                    val CinemaTextColor =
                        if (currentRoute == "Major_cinema") Color.Yellow else Color.White

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
                            color = CinemaTextColor,
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
                            color = FavoriteTextColor,
                            fontWeight = FontWeight.Bold
                        )
                    }


                }

                // LazyColumn for displaying filtered cinema items
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    modifier = Modifier.background(Color.Black)
                ) {
                    items(vm.favoriteItems) { item ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF292829))
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = item.title,
                                    fontSize = 16.sp,
                                    color = Color.White,
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(bottom = 4.dp)
                                )
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Favorite icon",
                                    tint = Color.White,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable { vm.toggleFavorite(item) } // Toggle favorite on click
                                )
                            }
                        }
                    }
                }
            }
        }

    )
}