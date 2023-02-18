package com.ipacsystemtest.livedata.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Update;

import com.ipacsystemtest.livedata.models.UserModel;

import java.util.List;

/**
 * Created by Shankar
 */

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveAll(List<UserModel> users);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void save(UserModel post);

    @Update
    void update(UserModel post);

}
