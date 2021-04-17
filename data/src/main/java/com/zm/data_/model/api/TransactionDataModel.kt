package com.zm.data_.model.api

import com.google.gson.annotations.SerializedName

data class TransactionDataModel(
    @SerializedName("x")
    val x: XDataModel
)