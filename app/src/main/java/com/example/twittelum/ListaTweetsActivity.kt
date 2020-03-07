package com.example.twittelum

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.twittelum.model.Tweet
import com.example.twittelum.viewmodel.TweetViewModel
import com.example.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_tweet_list.*

class ListaTweetsActivity: AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tweet_list)

        viewModel.lista().observe(this, obeserver())

        fabNewTweet.setOnClickListener{
            val intencao = Intent(this, TweetFormActivity::class.java)
            startActivity(intencao)
        }
    }

    private fun obeserver()= Observer<List<Tweet>> {
        tweetList.adapter = ArrayAdapter<Tweet>(this, android.R.layout.simple_list_item_1, it)
    }

}