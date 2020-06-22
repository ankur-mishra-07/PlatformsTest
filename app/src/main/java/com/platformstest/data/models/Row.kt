package com.platformstest.data.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Row(
    @SerializedName("description")
    var description: String? = "",
    @SerializedName("imageHref")
    var imageHref: String? = "",
    @SerializedName("title")
    var title: String? = ""
)