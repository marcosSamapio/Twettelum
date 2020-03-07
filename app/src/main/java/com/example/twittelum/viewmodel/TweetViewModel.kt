package com.example.twittelum.viewmodel

import androidx.lifecycle.ViewModel
import com.example.twittelum.db.TweetRepository
import com.example.twittelum.model.Tweet

class TweetViewModel(private val repository: TweetRepository): ViewModel() {
    fun lista() = repository.lista()
    fun salva (tweet: Tweet) = repository.salva(tweet)
}