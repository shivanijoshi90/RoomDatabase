
package com.example.roomdatabase5.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ContactEntity::class], version = 1)
abstract class DBHelper : RoomDatabase(){



    // DAO return
    abstract fun dao(): ContactDAO

    //db return
    companion object{
        val db : DBHelper? = null
        fun checkDB(context: Context):DBHelper{
            if (db != null)
            {
                return db
            }
            else
            {
                return initDB(context)
            }
        }
        fun initDB(context: Context):DBHelper
        {
            return  Room.databaseBuilder(context,DBHelper::class.java,"contacts.db")
                .allowMainThreadQueries().build()


        }
    }
}
