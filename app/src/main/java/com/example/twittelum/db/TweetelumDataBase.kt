package com.example.twittelum.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.twittelum.model.Tweet

@Database(entities = [Tweet::class], version = 1)
abstract class TweetelumDataBase: RoomDatabase() {
    abstract fun tweetDao(): TweetDao
    companion object {
        private var dataBase: TweetelumDataBase? = null
        private val DATABASE = "TwettelumDB"

        fun getInstance(context: Context): TweetelumDataBase {
            return dataBase?: criaBanco(context).also { dataBase = it }
        }

        private fun criaBanco(context: Context): TweetelumDataBase {
            return Room.databaseBuilder(context, TweetelumDataBase::class.java, DATABASE)
                .allowMainThreadQueries()
                .build()
        }
    }
}