package com.example.yujie.Major

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.example.yujie.mo.Cin
import com.example.yujie.model.Cinema

@Composable
fun ExpandableMajorView(cin: Cin) {
    var expanded by remember { mutableStateOf(false) }
    Column(modifier = Modifier.padding(8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expanded = !expanded }
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically // Aligns text, icon, and image vertically centered
        ) {
            AsyncImage(model = "https://www.majorcineplex.com.kh/load_file/cinema_brand/file_20221704051750.png", contentDescription = "")
            Spacer(modifier = Modifier.width(8.dp)) // Add space between image and text
            Text(
                text = cin.name,
                fontSize = 18.sp,
                color = Color.White,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                contentDescription = if (expanded) "Collapse" else "Expand",
                tint = Color.White
            )
        }
        AnimatedVisibility(visible = expanded) {
            Column {
                cin.movies.forEach { movie ->
                    Text(
                        text = " ${movie.format}",
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontSize = 12.sp
                    )
                    Text(
                        text = " ${movie.language.joinToString("|")}",
                        color = Color.White,
                        fontSize = 18.sp
                    )
                    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                        movie.showTimes.forEach { time ->
                            ShowTimeChip(time = time)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShowTimeChip(time: String) {
    Card(
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .border(1.dp, Color.White, RoundedCornerShape(20.dp))

    ) {
        Box(
            modifier = Modifier
                .height(40.dp)
                .width(80.dp)
//               .padding(horizontal = 10.dp, vertical = 4.dp)
                .background(Color(0xFF292829), shape = RoundedCornerShape(20.dp))

        ) {
            Text(
                text = time, fontSize = 12.sp, color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}


