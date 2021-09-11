package ru.lacars.cars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import ru.lacars.cars.ui.main.MainFragment
import ru.lacars.cars.ui.setting.SettingsFragment

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.main_activity)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(false)
        //supportActionBar?.


        //if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commit()
        //}



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
        /*if(item.itemId == R.id.toolbar_btn_filter){
            supportFragmentManager.beginTransaction()
                .addToBackStack("setting")
                .replace(R.id.container,SettingsFragment.newInstance())
                .commit()
        }*/

        return true
    }
}