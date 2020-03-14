package com.example.twittelum.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.twittelum.R
import com.example.twittelum.extension.Carregador
import com.example.twittelum.model.Tweet
import kotlinx.android.synthetic.main.item_tweet.view.*

class TweetAdapter(private val tweets: List<Tweet>): BaseAdapter() {

    override fun getCount(): Int = tweets.size

    override fun getItem(position: Int): Tweet = tweets[position]

    override fun getItemId(position: Int): Long = tweets[position].id.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val tweet = tweets[position]
        val inflater = LayoutInflater.from(parent?.context)
        val view = inflater.inflate(R.layout.item_tweet, parent, false)
        view.itemTweetTexto.text = tweet.conteudo

        tweet.foto?.let {
            view.itemTweetFoto.visibility = View.VISIBLE
            view.itemTweetFoto.setImageBitmap(Carregador.decodifica(it))
        }
        return view
    }

}