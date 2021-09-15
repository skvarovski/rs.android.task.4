package ru.lacars.cars.repository.cursor

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.flow.asFlow
import ru.lacars.cars.repository.room.Car
import java.util.concurrent.Flow

internal const val COLUMN_ID = "id"
internal const val COLUMN_NAME = "name"
internal const val COLUMN_COLOR = "color"
internal const val COLUMN_YEAR = "year"

private const val LOG_TAG = "TEST"
private const val DATABASE_NAME = "cars-database"
private const val TABLE_NAME = "cars"
private const val DATABASE_VERSION = 1

private const val CREATE_TABLE_SQL =
    "CREATE TABLE IF NOT EXISTS $TABLE_NAME " +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "$COLUMN_NAME TEXT NOT NULL, " +
            "$COLUMN_COLOR TEXT NOT NULL," +
            "$COLUMN_YEAR INTEGER NOT NULL);"

private const val SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS $TABLE_NAME"


class CarSQLiteOpenHelper(context: Context) : SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    null,
    DATABASE_VERSION
) {

    override fun onCreate(db: SQLiteDatabase) {
        try {
            db.execSQL(CREATE_TABLE_SQL)
            //(1..5).forEach { _ ->
                db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME,$COLUMN_COLOR,$COLUMN_YEAR) VALUES ('KIA','RED',2008);")
                db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME,$COLUMN_COLOR,$COLUMN_YEAR) VALUES ('AUDI','White',2005);")
                db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME,$COLUMN_COLOR,$COLUMN_YEAR) VALUES ('BMW','Blue',2003);")
                db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME,$COLUMN_COLOR,$COLUMN_YEAR) VALUES ('SUBARU','Green',2001);")
                db.execSQL("INSERT INTO $TABLE_NAME ($COLUMN_NAME,$COLUMN_COLOR,$COLUMN_YEAR) VALUES ('TOYOTA','Dark',2011);")

            //}
        } catch (exception: SQLException) {
            Log.e(LOG_TAG, "Exception while trying to create database", exception)
        }
    }

    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        super.onDowngrade(db, oldVersion, newVersion)
        onUpgrade(db, oldVersion, newVersion)

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        Log.d(LOG_TAG, "onUpgrade called")
        db.execSQL(SQL_DELETE_ENTRIES)
        onCreate(db)
    }

    fun saveCarCursor(car: Car): Long? {
        val db = this.writableDatabase
        // Create a new map of values, where column names are the keys
        val values = ContentValues().apply {
            put(COLUMN_NAME, car.name)
            put(COLUMN_COLOR, car.color)
            put(COLUMN_YEAR, car.year)
        }

        // Insert the new row, returning the primary key value of the new row
        return db?.insert(TABLE_NAME, null, values)


    }

    private fun getCursorWithTopics(): Cursor {
        return readableDatabase.rawQuery("SELECT * FROM $TABLE_NAME", null)
    }




    fun getListOfTopics(): List<Car> {
        val listOfCars = mutableListOf<Car>()
        getCursorWithTopics().use { cursor ->
            try {
                if (cursor.moveToFirst()) {
                    do {
                        val id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                        val name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME))
                        val color = cursor.getString(cursor.getColumnIndex(COLUMN_COLOR))
                        val year = cursor.getInt(cursor.getColumnIndex(COLUMN_YEAR))
                        listOfCars.add(Car(id,name,color,year))
                    } while (cursor.moveToNext())
                } else {
                    listOfCars

                }

            } catch (exception: SQLException) {
                Log.e(LOG_TAG, "Exception while reading table", exception)
            }
            finally {
                cursor.close()
            }
        }

        return listOfCars
    }
}