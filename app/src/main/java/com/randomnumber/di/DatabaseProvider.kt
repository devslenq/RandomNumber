package com.randomnumber.di

import android.content.Context
import androidx.room.Room
import com.randomnumber.core.data.storage.AppDatabase

fun provideAppDataDatabase(context: Context): AppDatabase {
    return Room.databaseBuilder(context, AppDatabase::class.java, "number_items")
        .fallbackToDestructiveMigration()
        .build()
}




