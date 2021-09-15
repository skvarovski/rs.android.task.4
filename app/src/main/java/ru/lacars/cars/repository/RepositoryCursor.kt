package ru.lacars.cars.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import ru.lacars.cars.locator.locateLazy
import ru.lacars.cars.repository.cursor.CarSQLiteOpenHelper
import ru.lacars.cars.repository.room.Car
import ru.lacars.cars.repository.room.CarsDatabase
import ru.lacars.cars.repository.room.IRepository

class RepositoryCursor(

): IRepository() {
    private val carSQLiteOpenHelper: CarSQLiteOpenHelper by locateLazy()


    override fun getAll(): Flow<List<Car>> =
                runBlocking {
                    flow {
                        val list = carSQLiteOpenHelper.getListOfTopics()
                        emit(list)
                    }
                }


    override suspend fun save(car: Car) = carSQLiteOpenHelper.saveCarCursor(car)


    override suspend fun delete(car: Car)  = carSQLiteOpenHelper.deleteCarCursor(car)






}