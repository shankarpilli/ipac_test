package com.ipacsystemtest.rest.features

import android.app.Application
import androidx.lifecycle.*
import com.ipacsystemtest.livedata.db.AppDatabase
import com.ipacsystemtest.livedata.db.UserDao
import com.ipacsystemtest.livedata.models.UserModel
import com.ipacsystemtest.rest.api.IPACApi
import com.ipacsystemtest.rest.api.data.UsersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.inject.Inject

@HiltViewModel
class UsersListViewModel @Inject constructor(
    api: IPACApi,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val NAME_KEY = "name"
    private val usersListLiveData = MutableLiveData<UsersList?>()
    val usersList: MutableLiveData<UsersList?> = usersListLiveData
    private var savedStateHandle: SavedStateHandle
    private var api: IPACApi

    /*private var application: Application
    private val userDao: UserDao
    private val executorService: ExecutorService*/

    private fun getUserRequest(): LiveData<Int> {
        return savedStateHandle.getLiveData<Int>(NAME_KEY)
    }

    fun getUsersListRequest(moviesListRequest: Int) {
        savedStateHandle.set<Int>(NAME_KEY, moviesListRequest)
    }
    fun getUsersListData() {
        viewModelScope.launch {
            val usersList = getUserRequest().value?.let {
                api.getMoviesList(
                    it
                )
            }
            usersListLiveData.value = usersList
        }
    }

    /*fun addAllItem(users: List<UserModel?>?) {
        executorService.execute { userDao.saveAll(users) }
    }*/

    init {
        this@UsersListViewModel.savedStateHandle = savedStateHandle
        this@UsersListViewModel.api = api
        /*this@UsersListViewModel.application = application

        userDao = AppDatabase.getInstance(application).userDao()
        executorService = Executors.newSingleThreadExecutor()*/
    }

}

