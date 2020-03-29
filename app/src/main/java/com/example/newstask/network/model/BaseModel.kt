package com.example.newstask.network.model

import com.google.gson.annotations.SerializedName

open class BaseResponse{
    @SerializedName("status")
    var state: String=""
    @SerializedName("message")
    var msg: String?=""

}