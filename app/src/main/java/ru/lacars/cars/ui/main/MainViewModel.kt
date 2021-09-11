package ru.lacars.cars.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import ru.lacars.cars.locator.locateLazy
import ru.lacars.cars.repository.Repository
import ru.lacars.cars.repository.room.Car

class MainViewModel : ViewModel() {

    private val repository: Repository by locateLazy()

    val cars = repository.getAll().asLiveDataFlow()
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