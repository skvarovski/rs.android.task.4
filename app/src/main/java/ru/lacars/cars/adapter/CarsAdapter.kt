package ru.lacars.cars.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import ru.lacars.cars.repository.room.Car


class CarsAdapter : ListAdapter<Car, CarViewHolder>(CarDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder =
        CarViewHolder.create(parent)

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) =
        holder.onBind(getItem(position))

}