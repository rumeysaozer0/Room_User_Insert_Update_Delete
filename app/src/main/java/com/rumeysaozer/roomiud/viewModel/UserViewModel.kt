package com.rumeysaozer.roomiud.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.rumeysaozer.roomiud.model.User
import com.rumeysaozer.roomiud.service.UserDatabase
import com.rumeysaozer.roomiud.service.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application): AndroidViewModel(application) {
    val readAllData: LiveData<List<User>>
    private val repository: UserRepository
    init{
        val userDao = UserDatabase(getApplication()).userDao()
        repository = UserRepository(userDao)
        readAllData = userDao.readAllData()

    }
    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
    fun updateUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateUser(user)
        }
    }
    fun deleteUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteUser(user)
        }
    }

}