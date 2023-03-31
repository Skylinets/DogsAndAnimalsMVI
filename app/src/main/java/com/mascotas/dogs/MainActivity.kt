package com.mascotas.dogs


import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvianimalscompose.view.MainIntent
import com.example.mvianimalscompose.view.MainViewModel
import com.example.mvianimalscompose.view.ViewModelFactory
import com.mascotas.dogs.api.AnimalService
import com.mascotas.dogs.ui.MyScaffold
import com.mascotas.dogs.ui.theme.DogsTheme
import kotlinx.coroutines.launch

class MainActivity : FragmentActivity() {

    private lateinit var mainViewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*mainViewModel = ViewModelProvider
            .of(this, ViewModelFactory(AnimalService.api))
            .get(MainViewModel::class.java)*/

        mainViewModel = ViewModelProvider(this, ViewModelFactory(AnimalService.api)).get(MainViewModel::class.java)

        val onButtonClick: () -> Unit = {
            lifecycleScope.launch{
                mainViewModel.userIntent.send(MainIntent.FetchAnimals)
            }
        }
        setContent {
            DogsTheme() {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    //MainScreen(vm = mainViewModel, onBottomClick = onButtonClick)
                    MyScaffold(vm = mainViewModel, onBottomClick = onButtonClick)
                }
            }
        }
    }
}



