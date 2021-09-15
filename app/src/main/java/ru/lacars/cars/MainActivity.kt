package ru.lacars.cars

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import ru.lacars.cars.repository.PreferencesOrder
import ru.lacars.cars.ui.main.MainFragment
import ru.lacars.cars.ui.setting.SettingsFragment

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.main_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        }


        PreferenceManager.getDefaultSharedPreferences(this)
        //val listener = Preference.OnPreferenceChangeListener()


        // { sharedPreferences, key ->
        //                if (key == "pref_room_key") {
        //                    val room = sharedPreferences?.getBoolean("pref_room_key",true)
        //                    supportActionBar?.title = "Cars. Room:$room"
        //                }
        //            }



    }

    override fun onResume() {
        super.onResume()
        /*val pref_sort = prefs.getString(getString(R.string.pref_sort), "id");
        val pref_use_room = prefs.getBoolean(getString(R.string.pref_room), false);
        supportActionBar?.title = "Cars. Room:$pref_use_room Sort:$pref_sort"*/

    }




    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_btn_filter -> {
                supportFragmentManager.beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.container,SettingsFragment.newInstance())
                    .commit()
            }
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return true
    }
}