package com.omdbapimovies.api

import android.content.Context
import androidx.room.Room
import com.ipacsystemtest.livedata.db.AppDatabase
import com.ipacsystemtest.livedata.db.UserDao
import com.ipacsystemtest.rest.api.IPACApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This class is used to provide retrofit client
 */
@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(IPACApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideOmdbapiMoviesApi(retrofit: Retrofit): IPACApi =
        retrofit.create(IPACApi::class.java)


   // Tell Dagger-Hilt to create a singleton accessible everywhere in ApplicationCompenent (i.e. everywhere in the application)
    @Provides
    @Singleton
   fun provideYourDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        AppDatabase::class.java,
        "Users.db"
    ).build()

    @Provides
    @Singleton
    fun provideUserDao(db: AppDatabase) = db.userDao()
}