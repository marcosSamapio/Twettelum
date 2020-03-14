package com.example.twittelum.extension

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.util.*

object Carregador {
    fun decodifica(foto: String): Bitmap {
        val byteArray = Base64.decode(foto, Base64.DEFAULT)
        val bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
        return bitmap
    }
}