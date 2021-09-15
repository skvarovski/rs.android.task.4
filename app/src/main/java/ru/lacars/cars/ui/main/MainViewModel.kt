package ru.lacars.cars.ui.main

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.*
import androidx.preference.PreferenceManager
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import ru.lacars.cars.locator.locateLazy
import ru.lacars.cars.repository.*
import ru.lacars.cars.repository.room.Car
import ru.lacars.cars.repository.room.IRepository
import java.util.*


class MainViewModel() : ViewModel() {

    //private val repository: Repository by locateLazy()
    private val preferencesOrder: PreferencesOrder by locateLazy()
    //private val preferencesDB: PreferencesDB by locateLazy()
    private val context: Context by locateLazy()
    //private val application: Application by locateLazy()

    private val repositoryRoom: RepositoryRoom = RepositoryRoom(context)
    private val repositoryCursor: RepositoryCursor = RepositoryCursor()
    lateinit var repository: IRepository

    //динамически не меняется, но если заюзать через перезагрузку приложения, то ОК
    val useRoom: Boolean by lazy {
        val prefs: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        prefs.getBoolean("pref_room_key", false)
    }


    init {
        Log.d("TEST","Change DB . isRoom = $useRoom")
        if (useRoom) {
            repository = RepositoryRoom(context)
        } else {
            repository = repositoryCursor
        }
    }

    /*repository by lazy {
        when (useRoom) {
            true -> repositoryRoom
            else -> repositoryCursor
        }
    }*/

    private val _cars = repository.getAll()

    fun updateList(): Flow<List<Car>> {
        return _cars.map { cars ->
            when (getPreferences().value)  {
                "id" -> {
                    Log.d("TEST","Sort by id")
                    cars.sortedBy { it.id }
                }
                "name" -> {
                    Log.d("TEST","Sort by name")
                    cars.sortedBy { it.name.lowercase(Locale.getDefault()) }
                }
                "color" -> {
                    Log.d("TEST","Sort by color")
                    cars.sortedBy { it.color.lowercase(Locale.getDefault()) }
                }
                "year" -> {
                    Log.d("TEST","Sort by year")
                    cars.sortedBy { it.year }
                }
                else -> cars
            }
        }.asLiveDataFlow()
    }

    fun getPreferences() = preferencesOrder

    fun delete(car: Car) {
        viewModelScope.launch {
            repository.delete(car)
        }
        repository.getAll()
    }

    fun save(car: Car) {
        viewModelScope.launch {
            repository.save(car)

        }

    }

    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)
}