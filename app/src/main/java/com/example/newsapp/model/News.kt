package com.example.newsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    var id: Int? = null,
    val title: String,
    val imageUrl: String,
    val url: String
) : Parcelable