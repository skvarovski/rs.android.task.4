package ru.lacars.cars.repository.room

import androidx.annotation.Keep
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "cars")
data class Car(
    @NotNull
    @ColumnInfo(name = "name")
    val name: String,
    @NotNull
    @ColumnInfo(name = "color")
    val color: String,
    @NonNull
    @ColumnInfo(name = "year")
    val year: Int,
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
)


