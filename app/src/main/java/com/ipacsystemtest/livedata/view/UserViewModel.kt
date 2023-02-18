package com.ipacsystemtest.livedata.view

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ipacsystemtest.livedata.db.AppDatabase
import com.ipacsystemtest.livedata.db.UserDao
import com.ipacsystemtest.livedata.models.UserModel
import com.ipacsystemtest.rest.api.IPACApi
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

/**
 * Created by Shaknar
 */
@HiltViewModel
class UserViewModel @Inject constructor(
    application: Application
) : ViewModel() {
    private val userDao: UserDao
    private val executorService: ExecutorService
    private var application: Application

    init {
        userDao = AppDatabase.getInstance(application).userDao()
        executorService = Executors.newSingleThreadExecutor()
    }

    fun addAllItem(users: List<UserModel?>?) {
        executorService.execute { userDao.saveAll(users) }
    }

    init {
        this@UserViewModel.application = application
    }
}