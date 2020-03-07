package com.example.twittelum.db

import androidx.room.Dao
import androidx.room.Insert
import com.example.twittelum.model.Tweet

@Dao
interface TweetDao {
    @Insert
    fun salva(tweet: Tweet)
}