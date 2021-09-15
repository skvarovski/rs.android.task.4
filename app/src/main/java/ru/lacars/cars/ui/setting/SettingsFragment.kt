package ru.lacars.cars.ui.setting

import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import androidx.preference.*
import ru.lacars.cars.R


class SettingsFragment : PreferenceFragmentCompat() {

    lateinit var prefs: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.settings)

        prefs = PreferenceManager.getDefaultSharedPreferences(requireContext())
        prefs.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            Log.d("TEST","key is $key")
        }


        val myList = preferenceManager.findPreference<Preference>("pref_sort_by_type") as ListPreference
        myList.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                myList.summary = newValue.toString()

                true
            }

        val mySwitch = preferenceManager.findPreference<Preference>("pref_room_key") as SwitchPreference
        mySwitch.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                when(newValue.toString()) {
                    "true" -> mySwitch.summary = "Use Room"
                    else -> mySwitch.summary = "Use Cursor"
                }
                //newValue.toString() === "true" ? "Use Room" : "Use Cursor"
                //mySwitch.summary = newValue.toString()

                true
            }


    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()

    }


    companion object {
        fun newInstance() = SettingsFragment()
    }

}