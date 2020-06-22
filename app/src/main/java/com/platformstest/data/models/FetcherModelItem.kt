package com.platformstest.data.models

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import androidx.annotation.Keep
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.gson.annotations.SerializedName


@Keep
data class FetcherModelItem(
    @SerializedName("rows")
    var rows: List<Row> = listOf(),
    @SerializedName("title")
    var title: String = ""
)

// important code for loading image here
@BindingAdapter("avatar")
fun loadImage(imageView: ImageView, imageURL: String?) {
    Glide.with(imageView.context)
        .setDefaultRequestOptions(
            RequestOptions()
                .circleCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
        )
        .load(imageURL)
        .error(R.mipmap.sym_def_app_icon)
        .into(imageView)
}