package com.mascotas.dogs.ui

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.model.*
import kotlinx.coroutines.launch

@Composable
fun MyScaffold(vm: MainViewModel) {
    val scaffoldState = rememberScaffoldState()
    val couroutineScope = rememberCoroutineScope()
    val navController = rememberNavController()
    val navigation_item = listOf(
        Pantalla1,
        Pantalla2,
        Pantalla3
    )

    Scaffold(topBar = {
        MyToolbar(
            onClickMenu = { couroutineScope.launch { scaffoldState.drawerState.open() } },
            onSearch = { couroutineScope.launch { vm.newSearch(it) } }

        )
    }, scaffoldState = scaffoldState,
        bottomBar = { MyBottomNavigation(navController, navigation_item) },
        /* floatingActionButton = { MyFAB() },*/
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
fun MyFAB() {
    FloatingActionButton(onClick = { }, backgroundColor = Color.Red, contentColor = Color.White) {

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
fun MyToolbar(onClickMenu: () -> Unit, onSearch: (String) -> Unit) {
    var searchText by remember {
        mutableStateOf("")
    }
    var smallSize by rememberSaveable { mutableStateOf(true) }
    val size by animateDpAsState(targetValue = if (smallSize) 1000.dp else 10000.dp,
        animationSpec = tween(durationMillis = 700),
        finishedListener = {}
    )
    var isVisible by remember { mutableStateOf(false) }
    TopAppBar(
        title = { AnimatedVisibility(!isVisible) { Text(text = "Mi primera Toolbar") } },
        backgroundColor = Color.Red,
        contentColor = Color.White,
        elevation = 15.dp,
        navigationIcon = {
            IconButton(onClick = { onClickMenu() }) {
                Icon(imageVector = Icons.Filled.Menu, contentDescription = "menu")
            }
        },
        actions = {
            IconButton(onClick = {
                isVisible = !isVisible
                //onSearch(searchText)

            }) {
                Row() {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search"
                    )
                    AnimatedVisibility(isVisible) {
                        OutlinedTextField(
                            value = searchText, onValueChange = { search ->
                                searchText = search
                                onSearch(search)
                            },
                            modifier = Modifier
                                .size(size)
                                .clickable {/*smallSize = !smallSize*/ /* onSearch(searchText)*/ },
                            singleLine = true, maxLines = 1
                        )
                    }
                }
            }
        }
    )
}


@Composable
fun MySearch() {

    var query: String by rememberSaveable { mutableStateOf("") }

    TextField(
        value = query,
        onValueChange = { },
        maxLines = 1,
        textStyle = MaterialTheme.typography.subtitle1,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    )

}


















