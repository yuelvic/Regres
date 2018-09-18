package io.github.regres.utils.databinding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import io.github.regres.R
import io.github.regres.adapter.MainAdapter
import io.github.regres.adapter.UserAdapter
import io.github.regres.data.entities.Resource
import io.github.regres.data.entities.User

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
fun setResourceData(recyclerView: RecyclerView, dataSet: List<Resource>?) {
    if (recyclerView.adapter is MainAdapter) {
        (recyclerView.adapter as MainAdapter).addResources(dataSet)
    }
}

@BindingAdapter("data")
fun setUserData(recyclerView: RecyclerView, dataSet: List<User>?) {
    if (recyclerView.adapter is UserAdapter) {
        (recyclerView.adapter as UserAdapter).setUsers(dataSet)
    }
}