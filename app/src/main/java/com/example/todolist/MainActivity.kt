package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.TodoListTheme
import com.example.todolist.view.HomeView
import com.example.todolist.view.SplashView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            TodoListTheme {
//                HomeView(navController = rememberNavController())
//            }
           AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {


    val navController = rememberNavController()
    TodoListTheme() {
        NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashView(navController) }
            composable("home") { HomeView(navController) }

        }
    }

}
