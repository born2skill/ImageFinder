package pl.born2skill.imagefinder.utils

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import pl.born2skill.imagefinder.ui.ImageItemAdapter
import pl.born2skill.imagefinder.data.MatchedImage

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MatchedImage>?) {
    val adapter = recyclerView.adapter as ImageItemAdapter
    adapter.submitList(data)
}