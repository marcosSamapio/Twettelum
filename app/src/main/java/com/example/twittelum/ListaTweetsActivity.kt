package com.example.twittelum

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.twittelum.adapter.TweetAdapter
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

        tweetList.setOnItemClickListener { _, _, position, _ ->
            val tweet = tweetList.getItemAtPosition(position) as Tweet
            perguntaSeQuerDeletar(tweet)
        }
    }

    private fun perguntaSeQuerDeletar(tweet: Tweet) {
        AlertDialog.Builder(this)
            .setIcon(R.drawable.ic_warning)
            .setTitle(R.string.delete_title_dialog_tweet)
            .setMessage(R.string.delete_message_dialog_tweet)
            .setPositiveButton(R.string.delete_positive_dialog_tweet) { _: DialogInterface, _: Int -> viewModel.deleta(tweet)}
            .setNegativeButton(R.string.delete_negative_dialog_tweet, null)
            .show()
    }

    private fun obeserver(): Observer<List<Tweet>> {
        return Observer { tweets ->
            tweets?.let {
                tweetList.adapter = TweetAdapter(tweets)
            }
        }
    }

}