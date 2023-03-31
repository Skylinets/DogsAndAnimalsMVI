package com.mascotas.dogs.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.model.*

@Composable
fun NavigationHost(navController: NavHostController, onBottomClick: () -> Unit, vm: MainViewModel) {
    NavHost(navController = navController, startDestination = Pantalla1.route) {
        composable(Pantalla1.route) {
            //MainScreen(vm, onBottomClick)
            Home(vm, onBottomClick)
        }
        composable(Pantalla2.route) {
            Person()
        }
        composable(Pantalla3.route) {
            Favorite()
        }
    }
}