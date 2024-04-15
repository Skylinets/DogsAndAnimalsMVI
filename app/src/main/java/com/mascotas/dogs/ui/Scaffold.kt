package com.mascotas.dogs.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.ui.navigation.Item_Menu
import com.mascotas.dogs.ui.navigation.NavigationHost
import com.mascotas.dogs.ui.navigation.Pantalla1
import com.mascotas.dogs.ui.navigation.Pantalla2
import com.mascotas.dogs.ui.navigation.Pantalla3
import kotlinx.coroutines.launch

@Composable
fun MyScaffold(vm: MainViewModel, onButtonClick: () -> Unit) {
    val scaffoldState = rememberScaffoldState()
    val couroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navigation_item = listOf(
        Pantalla1,
        Pantalla2,
        Pantalla3
    )

    Scaffold(topBar = {
        MyToolbar(onClickIcon = {
            couroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "Has apretado $it"
                )
            }
        }, onClickMenu = { couroutineScope.launch { scaffoldState.drawerState.open() } })
    }, scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(navController, navigation_item) },
        floatingActionButton = { MyFAB(onButtonClick) },
        floatingActionButtonPosition = FabPosition.End,
        isFloatingActionButtonDocked = false,
        drawerContent = { MyDrawer { couroutineScope.launch { scaffoldState.drawerState.close() } } })
    {
        Box(Modifier.padding(it)) {
            NavigationHost(navController, vm)
        }
    }

}


@Composable
fun MyDrawer(onClickMenu: () -> Unit) {
    Column(Modifier.padding(8.dp)) {
        Text(text = "Primera Opción", modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickMenu() }
            .padding(8.dp))
        Text(text = "Segunda Opción", modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickMenu() }
            .padding(8.dp))
        Text(text = "Tercera Opción", modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickMenu() }
            .padding(8.dp))
        Text(text = "Cuarta Opción", modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickMenu() }
            .padding(8.dp))
        Text(text = "Quinta Opción", modifier = Modifier
            .fillMaxWidth()
            .clickable { onClickMenu() }
            .padding(8.dp))

    }


}


@Composable
fun MyFAB(onButtonClick: () -> Unit) {
    FloatingActionButton(onClick = { onButtonClick.invoke() }, backgroundColor = Color.Red, contentColor = Color.White) {

        Icon(imageVector = Icons.Filled.Phone, contentDescription = "Phone")

    }

}

@Composable
fun CurrentRoute(navController: NavHostController): String? {
    val entry by navController.currentBackStackEntryAsState()
    return entry?.destination?.route
}

@Composable
fun MyBottomNavigation(navController: NavHostController, items: List<Item_Menu>) {
    BottomNavigation(backgroundColor = Color.Red, contentColor = Color.White) {
        val currentRoute = CurrentRoute(navController = navController)
        items.forEach { item ->
            BottomNavigationItem(selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = item.title)
                }, label = { Text(text = item.title) } 
            )
        }

    }


}


@Composable
fun MyToolbar(onClickIcon: (String) -> Unit, onClickMenu: () -> Unit) {

    TopAppBar(
        title = { Text(text = "Mi primera Toolbar") },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        elevation = 15.dp,
        navigationIcon = {
            IconButton(onClick = { onClickMenu() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = { onClickIcon("Buscar") }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search")
            }
            IconButton(onClick = { onClickIcon("Bloquear") }) {
                Icon(imageVector = Icons.Filled.Lock, contentDescription = "Block")
            }
        }
    )


}


















