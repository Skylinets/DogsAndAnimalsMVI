package com.mascotas.dogs.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.mvianimalscompose.view.MainViewModel
import com.mascotas.dogs.MainState
import com.mascotas.dogs.data.model.Animal
import com.mascotas.dogs.data.model.Dog
import com.mascotas.dogs.data.retrofit.AnimalService

@Composable
fun MainScreen(vm: MainViewModel) {
    val state = vm.state.value

    when (state) {
        is MainState.Loading -> LoadingScreen()
        is MainState.Animals -> AnimalsList(animals = state.animals)
        is MainState.Error -> {
            Toast.makeText(LocalContext.current, state.error, Toast.LENGTH_LONG).show()
        }

        else -> {}
    }

}

@Composable
fun MainScreenDog(vm: MainViewModel) {
    val state = vm.state.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }

    when (val currentState = state.value) {
        is MainState.Loading -> LoadingScreen()
        is MainState.LoadingProgress -> {
            LoadingScreenWithProgress(progress = currentState.progress)
        }
        is MainState.Dogs -> {
            LazyColumn {
                items(items = currentState.dogs) { dog ->
                    DogCard(dog = dog)
                    Divider(color = Color.LightGray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
                }
            }
        }
        is MainState.Error -> {
            if (!currentState.error.isNullOrEmpty()) {
                LaunchedEffect(currentState.error) {
                    snackbarHostState.showSnackbar(
                        message = currentState.error,
                        duration = SnackbarDuration.Long
                    )
                }
            }
        }
        else -> Log.d("MainScreenDog", "State is None")
    }
}
@Composable
fun DogCard(dog: Dog) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = dog.name, style = MaterialTheme.typography.h6)
            Spacer(modifier = Modifier.height(8.dp))
            Image(
                painter = rememberImagePainter(dog.image),
                contentDescription = dog.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
            )
        }
    }
}

@Composable
fun LoadingScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
    }
}
@Composable
fun LoadingScreenWithProgress(progress: Float) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LinearProgressIndicator(
                progress = progress,
                color = Color.Blue,
                backgroundColor = Color.Gray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .height(8.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "${(progress * 100).toInt()}%",
                style = MaterialTheme.typography.h6,
                color = Color.Black
            )
        }
    }
}


@Composable
fun AnimalsList(animals: List<Animal>) {
    LazyColumn {
        items(items = animals) {
            AnimalItem(animal = it)
            Divider(color = Color.LightGray, modifier = Modifier.padding(top = 4.dp, bottom = 4.dp))
        }
    }
}

@Composable
fun AnimalItem(animal: Animal) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .height(100.dp)
    ) {
        val url = AnimalService.BASE_URL_ANIMAL + animal.image
        val painter = rememberImagePainter(data = url)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier.size(100.dp),
            contentScale = ContentScale.FillHeight
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 4.dp)
        ) {
            Text(text = animal.name, fontWeight = FontWeight.Bold)
            Text(text = animal.location)
        }
    }
}