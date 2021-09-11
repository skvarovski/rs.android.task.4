package ru.lacars.cars.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.lacars.cars.databinding.CarListItemBinding
import ru.lacars.cars.repository.room.Car


class CarViewHolder(
    private val binding: CarListItemBinding,
) : RecyclerView.ViewHolder(binding.root) {

    var item: Car? = null
        private set

    fun onBind(item: Car) {
        this.item = item

        views {
            nameTextView.text = item.name
            colorTextView.text = item.color
            yearTextView.text = item.year.toString()
        }
    }

    private fun <T> views(block: CarListItemBinding.() -> T): T? = binding.block()

    companion object {
        fun create(parent: ViewGroup) = CarListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ).let(::CarViewHolder)
    }
}