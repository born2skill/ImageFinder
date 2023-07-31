package pl.born2skill.imagefinder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pl.born2skill.imagefinder.data.MatchedImage
import pl.born2skill.imagefinder.databinding.ListItemBinding

class ImageItemAdapter (private val onItemClicked: (MatchedImage) -> Unit) : ListAdapter<MatchedImage, ImageItemAdapter.ImageViewHolder>(DiffCallback) {

    class ImageViewHolder(private var binding: ListItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(image: MatchedImage) {
            binding.image = image
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = getItem(position)
        holder.itemView.setOnClickListener {
            onItemClicked(image)
        }
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