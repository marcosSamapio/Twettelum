package com.example.twittelum

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class TweetFormActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        publicarTweet.setOnClickListener {
            val conteudo = textTweet.text.toString()
            Toast.makeText(this, conteudo, Toast.LENGTH_LONG).show()
            finish()
        }
    }
}
