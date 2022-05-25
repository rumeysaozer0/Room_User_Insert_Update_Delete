package com.rumeysaozer.roomiud.service

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rumeysaozer.roomiud.model.User

@Dao
interface UserDAO {
    @Query(value = "SELECT * FROM users ORDER BY id ASC")
    fun readAllData(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)
    @Update
    suspend fun updateUser(user: User)
    @Delete
    suspend fun deleteUser(user: User)

}