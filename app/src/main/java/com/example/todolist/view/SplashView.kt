package com.example.todolist.view


import android.content.ContentValues.TAG
import android.content.Context
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.todolist.R
import com.example.todolist.utiles.isOnline
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

@Composable

fun SplashView(navController:NavController) {
    val context = LocalContext.current
    var _isOnline:Boolean by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(5000)
        _isOnline= isOnline(context)
        if (_isOnline){
            navController.navigate("home")
        }else{
            Toast.makeText(context, "No internet connection!", Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxHeight()
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Box(contentAlignment= Alignment.Center ,) {
                Image(
                    painter = painterResource(id = R.drawable.splash_background),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth()
                    , contentScale = ContentScale.Crop
                )
              Column( modifier = Modifier
                  .align(Alignment.Center) , horizontalAlignment = Alignment.CenterHorizontally ) {
                  Image(
                      painter = painterResource(id = R.drawable.logo),
                      contentDescription = "",
                      Modifier
                          .size(80.dp)
                  )
                  Spacer(modifier = Modifier.height(10.dp))
                  Text(text = "مایندستر" , color = Color.White , fontWeight = FontWeight.Bold , fontSize = 30.sp)
                  Text(text = "MINDSTER" , color = Color.White , fontWeight = FontWeight.Light , fontSize = 10.sp , letterSpacing=10.sp  )
              }
            }
        }

    }
}