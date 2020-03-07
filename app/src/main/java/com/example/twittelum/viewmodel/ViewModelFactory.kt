package com.example.twittelum.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.twittelum.db.TweetRepository
import com.example.twittelum.db.TweetelumDataBase

object ViewModelFactory: ViewModelProvider.Factory {
    private val database = TweetelumDataBase.getInstance(TweetelumAplication.getInstance())
    private val tweetRepository = TweetRepository(database.tweetDao())

    override fun <T : ViewModel?> create(modelClass: Class<T>): T = TweetViewModel(tweetRepository) as T
}