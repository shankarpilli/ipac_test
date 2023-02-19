package com.ipacsystemtest.livedata.db

import com.ipacsystemtest.livedata.models.UserModel
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val yourDAO: UserDao?
){
    suspend fun saveAllUsers(users: List<UserModel>) = yourDAO?.saveAll(users)
}