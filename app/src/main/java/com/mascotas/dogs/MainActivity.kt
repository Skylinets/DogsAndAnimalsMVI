package com.mascotas.dogs


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvianimalscompose.view.MainIntent
import com.example.mvianimalscompose.view.MainViewModel
import com.example.mvianimalscompose.view.ViewModelFactory
import com.mascotas.dogs.data.retrofit.AnimalService
import com.mascotas.dogs.ui.MyScaffold
import com.mascotas.dogs.ui.theme.DogsTheme
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel =
            ViewModelProvider(
                this,
                ViewModelFactory(
                    AnimalService.api,
                    AnimalService.apiDog
                )
            )[MainViewModel::class.java]

        val onButtonClick: () -> Unit = {
            lifecycleScope.launch {
                mainViewModel.userIntent.send(MainIntent.FetchAnimals)
            }
        }
        setContent {
            DogsTheme() {
                Surface(color = MaterialTheme.colors.background) {
                    MyScaffold(vm = mainViewModel, onButtonClick)
                }
            }
        }
    }
}



