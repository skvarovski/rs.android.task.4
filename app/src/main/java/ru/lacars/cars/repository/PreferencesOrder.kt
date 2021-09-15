package ru.lacars.cars.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager

class PreferencesOrder(context: Context) : LiveData<String>() {
    var valueRoom = false

    private val sharedPreferences: SharedPreferences =
            PreferenceManager.getDefaultSharedPreferences(context)

    private val mTokenSharedPreferenceListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences?, key: String? ->
            if (key == MY_KEY_SORT) {
                value = sharedPreferences?.getString(MY_KEY_SORT, "")
            }
            if (key == MY_KEY_ROOM) {
                valueRoom = sharedPreferences?.getBoolean(MY_KEY_ROOM,true) == true
            }
        }

    override fun onActive() {

        super.onActive()
        value = sharedPreferences.getString(MY_KEY_SORT, "")
        valueRoom = sharedPreferences.getBoolean(MY_KEY_ROOM, false)
        sharedPreferences.registerOnSharedPreferenceChangeListener(mTokenSharedPreferenceListener)
        Log.d("TEST","Load preference SORT = $value")
        Log.d("TEST","Load preference ROOM = $valueRoom")
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(mTokenSharedPreferenceListener)
    }

    companion object {
        private const val MY_KEY_SORT = "pref_sort_by_type"
        private const val MY_KEY_ROOM = "pref_room_key"

    }
}