package com.example.twittelum

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModelProviders
import com.example.twittelum.db.TweetelumDataBase
import com.example.twittelum.model.Tweet
import com.example.twittelum.viewmodel.TweetViewModel
import com.example.twittelum.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class TweetFormActivity : AppCompatActivity() {

    private var localDaFoto: String? = null
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 123 && resultCode == Activity.RESULT_OK) {
            carregaFoto()
        }
    }

    private fun carregaFoto() {
        val bitmap = BitmapFactory.decodeFile(localDaFoto)
        val bm = Bitmap.createScaledBitmap(bitmap, 500, 300, true)
        tweetFoto.setImageBitmap(bm)
        val fotoNaBase64 = bm.decodificaPara64()
        tweetFoto.tag = fotoNaBase64
        tweetFoto.scaleType = ImageView.ScaleType.FIT_XY
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tweetPublicar -> {
                publicaTweet()
                finish()
                true
            }

            R.id.tweetMenuCamera -> {
                tiraFoto()
            }

            android.R.id.home -> finish()
        }
        return false
    }

    private fun tiraFoto() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val caminhoFoto = defineLocalDaFoto()
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, caminhoFoto)
        startActivityForResult(cameraIntent, 123)
    }

    private fun defineLocalDaFoto(): Uri {
        localDaFoto = "${getExternalFilesDir(Environment.DIRECTORY_PICTURES)}/${System.currentTimeMillis()}.jpg"
        val arquivoFoto = File(localDaFoto)
        return FileProvider.getUriForFile(this, "tweetelum.fileprovider", arquivoFoto)
    }

    private fun publicaTweet() {
        val tweet: Tweet = criaTweet()
        viewModel.salva(tweet)
        Toast.makeText(this, "$tweet foi salvo", Toast.LENGTH_LONG).show()
    }

    private fun criaTweet(): Tweet {
        val campoMensagemTweet = findViewById<EditText>(R.id.textTweet)
        val mensagemDoTweet: String = campoMensagemTweet.text.toString()
        val foto: String? = tweetFoto.tag as String?
        return Tweet(mensagemDoTweet, foto)
    }
}
