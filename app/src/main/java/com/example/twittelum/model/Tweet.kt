package com.example.twittelum.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Tweet(val conteudo: String,
                 val foto: String?,
            @PrimaryKey(autoGenerate = true) val id: Int = 0) {
    override fun toString(): String {
        return conteudo
    }
}