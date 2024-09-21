package com.example.yujie.Major

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.QrCode2
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MajorBody(vm: MajorViewModel, navController: NavController) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    AsyncImage(
                        model = "https://cms-api-web.majorcineplex.com/media/fark5ea0/major-logo.png",
                        contentDescription = "Title",
                        modifier = Modifier.width(50.dp).height(50.dp)
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = Color.Black),
                actions = {
                    IconButton(onClick = { /* Handle QR Code Click */ }) {
                        Icon(Icons.Default.QrCode2, contentDescription = "QR Code")
                    }
                    IconButton(onClick = { navController.navigate("Search") }) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                    }

                }
            )
        },
        bottomBar = {
            ComposeMajorBottomBar(navController = navController, vm = vm)
        }
    ) { paddingValues ->
        LaunchedEffect(Unit) {
            vm.getMajorResultList()
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black)
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            when {
                vm.isLoading -> CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                vm.errorMessage.isNotEmpty() -> Text(
                    text = vm.errorMessage,
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
                else -> Column(
                    modifier = Modifier.fillMaxSize().padding(10.dp)
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route

                            val nowShowingTextColor = if (currentRoute == "home") Color.Red else Color.White
                            val comingSoonTextColor = if (currentRoute == "Coming") Color.Red else Color.White

                            IconButton(onClick = { vm.toggleMode() }) {
                                Icon(
                                    if (vm.isGridMode.value) Icons.Default.List else Icons.Default.FilterList,
                                    contentDescription = "Toggle Layout",
                                    tint = Color.White,
                                    modifier = Modifier.size(30.dp)

                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .clickable { navController.navigate("home") },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Now Showing",
                                    fontSize = 18.sp,
                                    color = nowShowingTextColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(4.dp)
                                    .clickable { navController.navigate("Coming") },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "Coming Soon",
                                    fontSize = 18.sp,
                                    color = comingSoonTextColor,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                    }

                    if (vm.isGridMode.value) {
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)  // Ensure the grid takes up remaining space in Column
                        ) {
                            items(vm.majorList) { element ->
                                Column(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            navController.navigate("detail/${element.id}")
                                        }
                                ) {
                                    Surface(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(180.dp)
                                    ) {
                                        AsyncImage(
                                            model = element.image,
                                            contentDescription = "Image of ${element.title}",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = element.release,
                                        color = Color.Yellow,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                    Text(
                                        text = element.title,
                                        color = Color.White,
                                        modifier = Modifier.padding(horizontal = 4.dp)
                                    )
                                }
                            }
                        }
                    } else {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) {
                            items(vm.majorList) { element ->
                                Row(
                                    modifier = Modifier
                                        .padding(8.dp)
                                        .fillMaxWidth()
                                        .clickable {
                                            navController.navigate("detail/${element.id}")
                                        }
                                ) {
                                    Surface(
                                        modifier = Modifier.size(100.dp)
                                    ) {
                                        AsyncImage(
                                            model = element.image,
                                            contentDescription = "Image of ${element.title}",
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.fillMaxSize()
                                        )
                                    }

                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = element.release,
                                            color = Color.Yellow,
                                            modifier = Modifier.padding(horizontal = 4.dp)
                                        )
                                        Text(
                                            text = element.title,
                                            color = Color.White,
                                            modifier = Modifier.padding(horizontal = 4.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
