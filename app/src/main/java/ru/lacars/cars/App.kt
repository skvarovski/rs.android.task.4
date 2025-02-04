package ru.lacars.cars

import android.app.Application
import android.content.Context
import ru.lacars.cars.locator.ServiceLocator
import ru.lacars.cars.locator.locate
import ru.lacars.cars.repository.PreferencesDB
import ru.lacars.cars.repository.PreferencesOrder
import ru.lacars.cars.repository.Repository
import ru.lacars.cars.repository.cursor.CarSQLiteOpenHelper
import ru.lacars.cars.repository.room.CarsDatabase
import ru.lacars.cars.repository.room.CarsDatabaseImpl


class App: Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceLocator.register<Context>(this)

        ServiceLocator.register(PreferencesOrder(locate()))
        ServiceLocator.register(CarSQLiteOpenHelper(locate()))


        //ServiceLocator.register(Repository(locate()))
        //ServiceLocator.register<CarsDatabase>(CarsDatabaseImpl.create(locate()))
        //ServiceLocator.register(PreferencesDB(locate()))




    }


}

