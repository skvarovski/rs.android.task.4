package ru.lacars.cars.repository.room

import kotlinx.coroutines.flow.Flow

abstract class IRepository {

    abstract fun getAll() : Flow<List<Car>>

    abstract suspend fun save(car: Car)

    abstract suspend fun delete(car: Car)

}