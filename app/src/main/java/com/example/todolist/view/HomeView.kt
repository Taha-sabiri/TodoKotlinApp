package com.example.todolist.view

import PR_Green
import PR_LIGHT_Green
import PR_LIGHT_ORANGE
import PR_LIGHT_PURPLE
import PR_ORANGE
import PR_PURPLE
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.data.ItemDataClass
import com.example.todolist.data.ItemRepository
import com.example.todolist.ui.ItemViewModel

@SuppressLint("Range")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView(navController: NavController, repository: ItemRepository) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var titleInput by remember { mutableStateOf("") }
    val viewModel = remember { ItemViewModel(repository) }


    val items by viewModel.allItems.collectAsState(initial = emptyList())
    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = { showBottomSheet = true },
            containerColor = PR_PURPLE,
            elevation = FloatingActionButtonDefaults.elevation(0.dp)
        ) {
            Icon(imageVector = Icons.Outlined.Add, contentDescription = "", tint = Color.White)
        }
    }) { PaddingValues ->
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(900.dp)
            ) {
                // Sheet content
                Column(Modifier.padding(20.dp)) {

                    TextField(
                        modifier = Modifier.fillMaxWidth(),
                        minLines = 2,

                        value = titleInput,
                        onValueChange = { item -> titleInput = item },
                        placeholder = { Text(text = "متن را وارد  کنید ... ") },
                        label = {
                            Text(
                                text = "شرح وظیفه"
                            )
                        })
                    Spacer(modifier = Modifier.height(10.dp))
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            viewModel.insert(ItemDataClass(title = titleInput))
                            showBottomSheet=false
                        }) {
                        Text("افزودن")
                    }
                }
            }
        }
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
                    InfoSection(items)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(text = "لیست وظایف روزانه")
                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        Modifier
                            .fillMaxHeight()
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                     if (items.isNotEmpty()){
                         items.forEachIndexed() { index, item ->
                             ItemWidget(item.title, item.isCheck , onRemove = {viewModel.delete(item)} , onCheckedChange = { isChecked ->
                                 viewModel.update(item.copy(isCheck = isChecked))

                             })
                         }
                     }else{
                         Column(
                             Modifier
                                 .fillMaxHeight().fillMaxWidth(),
                             verticalArrangement = Arrangement.Center,
                             horizontalAlignment = Alignment.CenterHorizontally
                         ){

                             Image(
                                 painter = painterResource(id = R.drawable.not_found),
                                 contentDescription = "",
                                 Modifier
                                     .size(150.dp).padding(vertical = 10.dp)
                             )

                             Text(text = "آیتمی وجود ندارد" , color = PR_PURPLE)
                             
                         }
                        
                     }


                        Spacer(modifier = Modifier.height(200.dp))
                    }

                }
                Image(
                    painterResource(id = R.drawable.gradient),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .align(Alignment.BottomEnd),
                    contentScale = ContentScale.Crop
                )

            }

        }
    }
}

@Composable
private fun ItemWidget(
    title: String,
    check: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        Modifier
            .background(PR_LIGHT_PURPLE, shape = RoundedCornerShape(10.dp))

            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Checkbox(checked = check, onCheckedChange = onCheckedChange)

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
                    text = title,
                    fontSize = 12.sp
                )
            }
        }
        Icon(
            imageVector = Icons.Outlined.Delete,
            contentDescription = "",
            tint = PR_PURPLE,
            modifier = Modifier.clickable { onRemove() })


    }
}

@Composable
private fun InfoSection(items: List<ItemDataClass>) {
    val totalChecked = items.count { it.isCheck }
    val totalUnChecked = items.count { !it.isCheck }

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
                Text(text = totalChecked.toString(), fontSize = 15.sp)
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
                Text(text = totalUnChecked.toString(), fontSize = 15.sp)
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
            Text(text = "برنامتو با من بچین !!")
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