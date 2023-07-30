package pl.born2skill.imagefinder

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import pl.born2skill.imagefinder.data.MatchedImage

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        imgView.load(imgUri){
            //placeholder(R.drawable.loading_animation)
            //error(R.drawable.ic_broken_image)
        }
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<MatchedImage>?) {
    val adapter = recyclerView.adapter as ImageGridAdapter
    adapter.submitList(data)
}