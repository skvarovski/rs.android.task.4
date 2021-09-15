package ru.lacars.cars.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.preference.PreferenceManager


class PreferencesDB(context: Context) : LiveData<Boolean>() {


    private val sharedPreferences1: SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    private val mTokenSharedPreferenceListener =
        SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences: SharedPreferences?, key: String? ->
            if (key == MY_KEY_ROOM) {
                value = sharedPreferences?.getBoolean(MY_KEY_ROOM, true)
            }
        }

    override fun onActive() {
        Log.d("TEST","Load preference DB")
        super.onActive()
        value = sharedPreferences1.getBoolean(MY_KEY_ROOM, true)
        sharedPreferences1.registerOnSharedPreferenceChangeListener(mTokenSharedPreferenceListener)
    }

    override fun onInactive() {
        super.onInactive()
        sharedPreferences1.unregisterOnSharedPreferenceChangeListener(mTokenSharedPreferenceListener)
    }

    companion object {
        private const val MY_KEY_ROOM = "pref_room_key"

    }
}