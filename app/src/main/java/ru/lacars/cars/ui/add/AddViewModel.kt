package ru.lacars.cars.ui.add

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import ru.lacars.cars.locator.locateLazy
import ru.lacars.cars.repository.Repository
import ru.lacars.cars.repository.room.Car
import java.util.*

class AddViewModel : ViewModel() {
    private val repository: Repository by locateLazy()

    public fun save(car: Car) {
        viewModelScope.launch {
            repository.save(car)
        }

    }



    private fun <T> Flow<T>.asLiveDataFlow() =
        shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)






}