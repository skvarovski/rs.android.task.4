package ru.lacars.cars.adapter

import androidx.recyclerview.widget.ItemTouchHelper
import ru.lacars.cars.repository.room.Car

class SwipeHelper(onSwiped: (Car) -> Unit,): ItemTouchHelper(SwipeCallback(onSwiped))