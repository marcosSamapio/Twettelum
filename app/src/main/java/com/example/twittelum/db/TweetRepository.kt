package com.example.twittelum.db

import com.example.twittelum.model.Tweet

class TweetRepository(private val fonteDeDados: TweetDao) {
    fun lista() = fonteDeDados.lista()
    fun salva(tweet: Tweet) = fonteDeDados.salva(tweet)
}