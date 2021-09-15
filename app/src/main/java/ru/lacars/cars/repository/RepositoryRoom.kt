package ru.lacars.cars.repository

import android.app.Application
import android.content.Context
import androidx.room.Room
import kotlinx.coroutines.flow.Flow
import ru.lacars.cars.repository.room.Car
import ru.lacars.cars.repository.room.CarsDatabaseImpl
import ru.lacars.cars.repository.room.IRepository

class RepositoryRoom(
    context: Context
) : IRepository() {

    private val db = Room.databaseBuilder(
        context,
        CarsDatabaseImpl::class.java,
        "cars-database"
    ).build()

    //: CarsDatabase by locateLazy()

    private val dao get() = db.carsDao

    override fun getAll(): Flow<List<Car>> = dao.getAll()

    override suspend fun save(car: Car) = dao.add(car)


    override suspend fun delete(car: Car) = dao.delete(car)


/*private suspend fun saveRoom(car: Car) = dao.add(car)
private suspend fun saveCursor(car: Car) {

    carSQLiteOpenHelper.saveCarCursor(car)
}*/
}