package io.github.regres.utils.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.regres.R
import io.github.regres.adapter.MainAdapter
import io.github.regres.data.entities.Resource

@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String) {
    val requestOptions = RequestOptions()
//    requestOptions.placeholder(R.drawable.ic_movie)
//    requestOptions.error(R.drawable.ic_movie)
    Glide.with(imageView.context)
            .setDefaultRequestOptions(requestOptions)
            .load(url).into(imageView)
}

@BindingAdapter("data")
fun setData(recyclerView: RecyclerView, dataSet: List<Resource>?) {
    if (recyclerView.adapter is MainAdapter) {
        (recyclerView.adapter as MainAdapter).addResources(dataSet)
    }
}