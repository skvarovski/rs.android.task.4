package ru.lacars.cars.repository.room

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Keep
@Entity(tableName = "cars")
data class Car(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @NonNull
    val name: String,
    @NonNull
    val color: String,
    @NonNull
    val year: Int
)


