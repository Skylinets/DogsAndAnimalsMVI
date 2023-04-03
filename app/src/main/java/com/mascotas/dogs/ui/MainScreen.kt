package com.mascotas.dogs.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.MainState
import com.mascotas.dogs.api.AnimalService
import com.mascotas.dogs.model.Animal

@Composable
fun MainScreen(vm: MainViewModel){
    val state = vm.state.value

    when(state){
        is MainState.Loading -> LoadingScreen()
        is MainState.Animals -> AnimalsList(animals = state.animals)
        is MainState.Error -> {
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
        }
        else -> {}
    }

}


@Composable
fun IdleScreen(onButtonClick: () -> Unit){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Button(onClick = onButtonClick) {
            Text(text = "Fetch Animals")
        }
    }
}

@Composable
fun LoadingScreen(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        CircularProgressIndicator()
    }
}


@Composable
fun AnimalsList(animals: List<Animal>){
    LazyColumn {
        items(items = animals){
            AnimalItem(animal = it)
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
        }
    }
}

@Composable
fun AnimalItem(animal: Animal){
    Row(modifier = Modifier
        .fillMaxSize()
        .height(100.dp)) {
        val url = AnimalService.BASE_URL_ANIMAL + animal.image
        val painter = rememberImagePainter(data = url)
        Image(painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillHeight)
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(start = 4.dp)) {
            Text(text = animal.name, fontWeight = FontWeight.Bold)
            Text(text = animal.location)
        }
    }
}



