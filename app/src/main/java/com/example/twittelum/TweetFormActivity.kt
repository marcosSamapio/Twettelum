package com.example.twittelum

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.twittelum.db.TweetelumDataBase
import com.example.twittelum.model.Tweet
import kotlinx.android.synthetic.main.activity_main.*

class TweetFormActivity : AppCompatActivity() {

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
        val conteudoDoTweet = findViewById<EditText>(R.id.textTweet)
        val mensagemDoTweet = conteudoDoTweet.text.toString()
        val tweet = Tweet(mensagemDoTweet)
        val tweetDao = TweetelumDataBase.getInstance(this).tweetDao()
        tweetDao.salva(tweet)

        val conteudo = textTweet.text.toString()
        Toast.makeText(this, conteudo, Toast.LENGTH_LONG).show()
    }
}
