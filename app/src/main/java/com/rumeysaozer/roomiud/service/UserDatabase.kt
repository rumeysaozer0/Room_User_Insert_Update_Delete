package com.rumeysaozer.roomiud.service

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rumeysaozer.roomiud.model.User

@Database(entities = [User::class],version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDAO
    companion object{
        @Volatile
        private var instance: UserDatabase? = null
        private val lock = Any()
        operator fun invoke(context: Context) = instance ?: synchronized(lock){
           instance ?: makeDatabase(context).also {
               instance = it
           }
        }
        private fun makeDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext, UserDatabase::class.java, "userdatabase"
        ).build()
    }
}