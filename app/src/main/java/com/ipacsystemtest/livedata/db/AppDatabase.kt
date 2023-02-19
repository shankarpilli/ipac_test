package com.ipacsystemtest.livedata.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ipacsystemtest.livedata.models.UserModel
@Database(
    entities = [UserModel::class], // Tell the database the entries will hold data of this type
    version = 1
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao?
}
