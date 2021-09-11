package ru.lacars.cars.ui.setting

import android.os.Bundle
import android.content.SharedPreferences
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.preference.*
import ru.lacars.cars.App
import ru.lacars.cars.MainActivity
import ru.lacars.cars.R
import ru.lacars.cars.ui.add.AddFragment


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




        /*var onSharedPreferenceChangeListener = SharedPreferences.OnSharedPreferenceChangeListener { sharedPreferences, key ->
            Log.d("TEST","prefs")
               if (key == "aaaa") {
                   val pref_sort_value  = prefs.getString(getString(R.string.pref_sort), "");
                   Log.d("TEST","Pref = $pref_sort_value")
                   Toast.makeText(context, "Sort is $pref_sort_value", Toast.LENGTH_SHORT).show()
               }
           }
       prefs.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)*/


        /*prefs.registerOnSharedPreferenceChangeListener { sharedPreferences, key ->
            Log.d("TEST","key = $key")

        }*/

    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        return
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val actionBar = this.requireActivity().actionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true);
    }

    override fun onResume() {
        super.onResume()

        //var pref1 = prefs.getString(getString(R.string.pref_sort), "");



        //Log.d("TEST","Select = $pref1")
    }

    override fun onDestroy() {
        super.onDestroy()

    }

    /*override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }*/

    companion object {
        fun newInstance() = SettingsFragment()
    }

}