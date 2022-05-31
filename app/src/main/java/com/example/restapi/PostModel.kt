package com.example.restapi

import android.widget.ImageView
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import org.json.JSONObject

data class PostModel (
    @SerializedName("data")
    var data: List<information>,
    @SerializedName("page")
    var page: Int,
    @SerializedName("per_page")
    var perPage: Int,
    @SerializedName("support")
    var support: Support,
    @SerializedName("total")
    var total: Int,
    @SerializedName(
        "total_pages")
    var totalPages: Int
)