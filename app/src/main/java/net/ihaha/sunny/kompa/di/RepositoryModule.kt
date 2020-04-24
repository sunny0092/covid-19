package net.ihaha.sunny.kompa.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import net.ihaha.sunny.kompa.Constants
import org.koin.dsl.module

val repositoryModule = module {
    single { createDatabaseName() }
//    single { createAppDatabase(get(), get()) }
//    single { createMovieDao(get()) }
    single { Gson() }
}

fun createDatabaseName() = Constants.DATABASE_NAME

//fun createAppDatabase(dbName: String, context: Context) = Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()

//fun createMovieDao(appDatabase: AppDatabase) = appDatabase.movieDao()
