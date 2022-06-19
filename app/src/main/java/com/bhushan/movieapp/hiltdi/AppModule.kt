package com.bhushan.movieapp.hiltdi

import android.content.Context
import androidx.room.Room
import com.bhushan.movieapp.data.api.MovieService
import com.bhushan.movieapp.data.local.MovieDatabase
import com.bhushan.movieapp.utils.Constants
import com.bhushan.movieapp.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofitInterface(): MovieService {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(Constants.BASE_URL)
            .build().create(MovieService::class.java)
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext app: Context): MovieDatabase =
        Room.databaseBuilder(app, MovieDatabase::class.java, DATABASE_NAME).build()
}