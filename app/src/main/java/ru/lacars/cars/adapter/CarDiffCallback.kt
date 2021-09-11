package ru.lacars.cars.adapter

import androidx.recyclerview.widget.DiffUtil
import ru.lacars.cars.repository.room.Car

class CarDiffCallback : DiffUtil.ItemCallback<Car>() {
    override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean = oldItem == newItem

}