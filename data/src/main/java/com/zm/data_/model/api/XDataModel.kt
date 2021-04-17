package com.zm.data_.model.api

import com.google.gson.annotations.SerializedName

data class XDataModel(
    @SerializedName("size")
    val size: Int,
    @SerializedName("time")
    val time: Long
)