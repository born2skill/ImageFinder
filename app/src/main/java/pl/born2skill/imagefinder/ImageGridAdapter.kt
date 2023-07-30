package pl.born2skill.imagefinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.born2skill.imagefinder.data.MatchedImage
import pl.born2skill.imagefinder.databinding.GridViewItemBinding

class ImageGridAdapter : ListAdapter<MatchedImage, ImageGridAdapter.ImageViewHolder>(DiffCallback) {

    class ImageViewHolder(private var binding: GridViewItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: MatchedImage) {
            binding.photo = image
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.bind(image)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MatchedImage>() {
        override fun areItemsTheSame(oldItem: MatchedImage, newItem: MatchedImage): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MatchedImage, newItem: MatchedImage): Boolean {
            return oldItem.previewURL == newItem.previewURL
        }
    }
}