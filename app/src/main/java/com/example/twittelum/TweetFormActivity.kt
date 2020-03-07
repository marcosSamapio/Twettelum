package com.example.twittelum

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.example.twittelum.db.TweetelumDataBase
import com.example.twittelum.model.Tweet
import com.example.twittelum.viewmodel.TweetViewModel
import com.example.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class TweetFormActivity : AppCompatActivity() {

    private val viewModel: TweetViewModel by lazy {
        ViewModelProviders.of(this, ViewModelFactory).get(TweetViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tweet_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tweetPublicar -> {
                publicaTweet()
                finish()
                true
            }
            android.R.id.home -> finish()
        }
        return false
    }

    private fun publicaTweet() {
        val conteudo = textTweet.text.toString()
        salvaTweet(conteudo)
        Toast.makeText(this, conteudo, Toast.LENGTH_LONG).show()
    }

    private fun salvaTweet(conteudo: String) {
        val tweet = Tweet(conteudo)
        viewModel.salva(tweet)
    }
}
