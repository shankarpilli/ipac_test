package com.ipacsystemtest.rest.features

import android.app.Application
import androidx.lifecycle.*
import com.ipacsystemtest.livedata.db.AppDatabase
import com.ipacsystemtest.livedata.db.UserDao
import com.ipacsystemtest.livedata.db.UserRepository
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
    savedStateHandle: SavedStateHandle,
    val repository: UserRepository
) : ViewModel() {

    private val NAME_KEY = "name"
    private val usersListLiveData = MutableLiveData<UsersList?>()
    val usersList: MutableLiveData<UsersList?> = usersListLiveData
    private var savedStateHandle: SavedStateHandle
    private var api: IPACApi
    private var users: ArrayList<UserModel> = ArrayList()

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

            usersList?.results?.forEachIndexed { _, user ->
                val userModel  = UserModel()
                userModel.user_name = user.name.first + " "+user.name.first
                userModel.user_gender = user.gender.toString()
                userModel.user_location = user.location?.city+ " "+user.location?.state
                userModel.user_picture = user.picture.large
                users.add(userModel)
            }
            saveAllUsers(users)
        }
    }

    suspend fun saveAllUsers(users: List<UserModel>): Unit? {
        return repository.saveAllUsers(users)
    }

    init {
        this@UsersListViewModel.savedStateHandle = savedStateHandle
        this@UsersListViewModel.api = api
    }

}

