package ru.lacars.cars.ui.main

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import ru.lacars.cars.locator.locateLazy
import ru.lacars.cars.repository.PreferencesDB
import ru.lacars.cars.repository.PreferencesOrder
import ru.lacars.cars.repository.Repository
import ru.lacars.cars.repository.room.Car


class MainViewModel(
    //private val preferencesOrder: PreferencesOrder
) : ViewModel() {

    private val repository: Repository by locateLazy()
    private val preferencesOrder: PreferencesOrder by locateLazy()
    private val preferencesDB: PreferencesDB by locateLazy()


    //val cars = repository.getAll().asLiveDataFlow()
    private val _cars = repository.getAll().asLiveData()

    fun updateList() =  _cars.map { cars ->
        when (getPreferences().value)  {
             "id" -> {
                 Log.d("TEST","Sort by id")
                 cars.sortedBy { it.id }
             }
            "name" -> {
                Log.d("TEST","Sort by name")
                cars.sortedBy { it.name }
            }
            "color" -> {
                Log.d("TEST","Sort by color")
                cars.sortedBy { it.color }
            }
            "year" -> {
                Log.d("TEST","Sort by year")
                cars.sortedBy { it.year }
            }
            else -> cars
        }
    }

    fun getPreferences() = preferencesOrder
    fun getPreferencesBaseRoom() = preferencesDB

    /*val newCaption = flow<String> {
        while (true) {
            emit(createCaption())
            delay(500L)
        }
    }*/

    /*fun save(note: String) {
        viewModelScope.launch { repository.save(createNote(note)) }
    }*/

    fun delete(car: Car) {
        viewModelScope.launch { repository.delete(car) }
    }

    /*private fun createNote(noteText: String) = Car(
        name = createCaption(),
        color = noteText
    )*/

    /*private fun createCaption(): String =
        DateFormat.format("hh:mm:ss, MMM dd, yyyy", Date()).toString()*/

    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)
}