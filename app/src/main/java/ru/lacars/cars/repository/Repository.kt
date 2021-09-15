package ru.lacars.cars.repository

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import androidx.preference.PreferenceManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import ru.lacars.cars.R
import ru.lacars.cars.locator.locateLazy
import ru.lacars.cars.repository.cursor.CarSQLiteOpenHelper
import ru.lacars.cars.repository.room.Car
import ru.lacars.cars.repository.room.CarsDatabase

class Repository(
    private val db: CarsDatabase,
) {
    //private val preferencesDB: PreferencesDB by locateLazy()
    private val preferencesOrder: PreferencesOrder by locateLazy()
    private val context: Context by locateLazy()
    private val carSQLiteOpenHelper: CarSQLiteOpenHelper by locateLazy()
    private val dao get() = db.notesDao
    //private val _flowCursor = MutableLiveData<List<Car>>()
    //val flowCursor: LiveData<List<Car>> = _flowCursor

    private var prefs = PreferenceManager.getDefaultSharedPreferences(context) //preferencesOrder

    private var valueRoom =  prefs.getBoolean("pref_room_key", false)


    //fun getAll(): Flow<List<Car>> = dao.getAll()
    fun getAll(): Flow<List<Car>> {
        Log.d("TEST","PreferenceDB Repo = $valueRoom")

        return when (valueRoom) {
            true -> dao.getAll()
            else -> {
                return flow {
                    val list = carSQLiteOpenHelper.getListOfTopics()
                    emit(list)
                }.flowOn(Dispatchers.IO)

            }
        }

    }




    //suspend fun save(note: Car) = dao.add(note)
    suspend fun save(car: Car) {
        when (valueRoom) {
            true -> dao.add(car)
            else -> carSQLiteOpenHelper.saveCarCursor(car)
        }

    }

    suspend fun delete(note: Car) = dao.delete(note)


    /*private fun getAllRoom(): LiveData<List<Car>> {
        Log.d("TEST","Room load")
        return dao.getAll()
    }
    private fun getAllCursor(): LiveData<List<Car>> {
        Log.d("TEST","Cursor load")
        return carSQLiteOpenHelper.getListOfTopics()

    }*/



    /*private suspend fun saveRoom(car: Car) = dao.add(car)
    private suspend fun saveCursor(car: Car) {

        carSQLiteOpenHelper.saveCarCursor(car)
    }*/




}