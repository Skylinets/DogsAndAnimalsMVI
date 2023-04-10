package com.mascotas.dogs.model

import com.mascotas.dogs.R


sealed class Item_Menu(
    val icon: Int,
    val title: String,
    val route: String
)
object Pantalla1: Item_Menu(
  R.drawable.home_bottom, "Animal", "pantalla1"
)
object Pantalla2: Item_Menu(
  R.drawable.person_bottom, "Person", "pantalla2"
)
object Pantalla3: Item_Menu(
  R.drawable.star_bottom, "Favorite", "pantalla3"
)

