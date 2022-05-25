package com.rumeysaozer.roomiud.service

import androidx.lifecycle.LiveData
import com.rumeysaozer.roomiud.model.User

class UserRepository(private val userDAO: UserDAO) {
    val readAllData: LiveData<List<User>> = userDAO.readAllData()
    suspend fun addUser(user: User){
        userDAO.addUser(user)
    }
    suspend fun updateUser(user: User){
        userDAO.updateUser(user)
    }
    suspend fun deleteUser(user: User){
        userDAO.deleteUser(user)
    }
}