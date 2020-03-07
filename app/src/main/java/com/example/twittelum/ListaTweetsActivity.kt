package com.example.twittelum

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.snackbar.SnackbarContentLayout
import kotlinx.android.synthetic.main.activity_tweet_list.*

class ListaTweetsActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)

        val tweets = listOf(
            "Tweet 01",
            "hahaha",
            "mo fome"
        )

        val adapterList = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tweets)
        tweetList.adapter = adapterList

        fabNewTweet.setOnClickListener{
            Snackbar.make(it, "FAB clicado", Snackbar.LENGTH_LONG).show()

            val intencao = Intent(this, TweetFormActivity::class.java)
            startActivity(intencao)
        }
    }
}