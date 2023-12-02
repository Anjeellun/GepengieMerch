package com.dicoding.gepengiemerchandise

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Merch(
    val name: String,
    val description: String,
    val photo: Int,
) : Parcelable
