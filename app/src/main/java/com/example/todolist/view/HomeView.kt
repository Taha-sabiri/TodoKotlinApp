package com.example.todolist.view

import PR_Green
import PR_LIGHT_Green
import PR_LIGHT_ORANGE
import PR_LIGHT_PURPLE
import PR_ORANGE
import PR_PURPLE
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController) {
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            containerColor = PR_PURPLE,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "", tint = Color.White)
        }
    }) { PaddingValues ->
        Column(
            modifier = Modifier
                .padding(PaddingValues)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(contentAlignment = Alignment.Center) {
                Image(
                    painter = painterResource(id = R.drawable.background),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(), contentScale = ContentScale.Crop
                )
                Column(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                        .padding(top = 30.dp, start = 30.dp, end = 30.dp)
                ) {

                    HeaderSection()
                    InfoSection()
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "لیست وظایف روزانه")
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        ItemWidget()
                        ItemWidget()
                        ItemWidget()
                        ItemWidget()
                        ItemWidget()
                        ItemWidget()
                        Spacer(modifier = Modifier.height(200.dp))
                    }

                }
                Image(painterResource(id = R.drawable.gradient), contentDescription = "" , modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .align(Alignment.BottomEnd), contentScale = ContentScale.Crop)

            }

        }
    }
}

@Composable
private fun ItemWidget() {
    Row(
        Modifier
            .background(PR_LIGHT_PURPLE, shape = RoundedCornerShape(10.dp))

            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(checked = true, onCheckedChange = {})

        Column {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "",
                        Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "امروز ",
//                                    modifier = Modifier.weight(1f), // Make Text take remaining space
//                                    overflow = TextOverflow.Visible,
                        fontSize = 10.sp
                    )

                }
                Spacer(modifier = Modifier.width(20.dp))
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Outlined.DateRange,
                        contentDescription = "",
                        Modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "20:30",
//                                    modifier = Modifier.weight(1f), // Make Text take remaining space
//                                    overflow = TextOverflow.Visible,
                        fontSize = 10.sp
                    )

                }
            }
            Spacer(modifier = Modifier.height(5.dp))

            Row(Modifier.fillMaxWidth(0.9f)) {
                Text(
                    text = "لورم ایپسوم متن ساختگی با تولید سادگی نامفهوم از صنعت چاپ و با استفاده از طراحان گرافیک است",
                    fontSize = 12.sp
                )
            }
        }
        Icon(imageVector = Icons.Outlined.MoreVert, contentDescription = "", tint = PR_PURPLE)


    }
}

@Composable
private fun InfoSection() {
    Row(Modifier.fillMaxWidth()) {
        Column(
            Modifier
                .background(PR_LIGHT_Green, shape = RoundedCornerShape(10.dp))
                .height(100.dp)
                .fillMaxHeight()
                .fillMaxWidth(0.54f)
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                imageVector = Icons.Filled.CheckCircle,
                contentDescription = "",
                tint = PR_Green,
                modifier = Modifier.size(30.dp)
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "انجام شده")
                Text(text = "10", fontSize = 15.sp)
            }

        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(
            Modifier
                .background(PR_LIGHT_ORANGE, shape = RoundedCornerShape(10.dp))
                .height(100.dp)
                .fillMaxHeight()
                .padding(horizontal = 20.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                imageVector = Icons.Filled.Info,
                contentDescription = "",
                tint = PR_ORANGE,
                modifier = Modifier.size(30.dp)
            )

            Row(
                Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "انجام نشده")
                Text(text = "10", fontSize = 15.sp)
            }

        }
    }
}

@Composable
private fun HeaderSection() {
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Column {
            Text(
                text = "به مایندستر خوش آمدید \uD83D\uDE09 ",
                color = PR_PURPLE,
                fontWeight = FontWeight.Black,
                fontSize = 18.sp
            )
            Text(text = "برنامتو با ما بچین !!")
            Spacer(modifier = Modifier.height(15.dp))

        }

        IconButton(
            onClick = { /*TODO*/ },
            colors = IconButtonDefaults.iconButtonColors(
                containerColor = PR_LIGHT_ORANGE,
                contentColor = PR_ORANGE
            ),
        ) {
            Icon(imageVector = Icons.Outlined.Notifications, contentDescription = "")
        }
    }
}