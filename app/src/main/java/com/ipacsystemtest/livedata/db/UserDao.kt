package com.ipacsystemtest.livedata.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update
import com.ipacsystemtest.livedata.models.UserModel

/**
 * Created by Shankar
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAll(users: List<UserModel?>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(post: UserModel?)

    @Update
    suspend fun update(post: UserModel?)
}