package ru.lacars.cars.repository

import kotlinx.coroutines.flow.Flow
import ru.lacars.cars.repository.room.Car
import ru.lacars.cars.repository.room.CarsDatabase

class Repository(
    private val db: CarsDatabase,
) {

    private val dao get() = db.notesDao

    fun getAll(): Flow<List<Car>> = dao.getAll()

    suspend fun save(note: Car) = dao.add(note)

    suspend fun delete(note: Car) = dao.delete(note)

}