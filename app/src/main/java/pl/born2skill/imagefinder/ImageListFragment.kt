package pl.born2skill.imagefinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import pl.born2skill.imagefinder.databinding.FragmentImageListBinding

class ImageListFragment : Fragment() {

    private val viewModel: ImageViewModel by activityViewModels()
    private var _binding: FragmentImageListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = ImageItemAdapter{
            viewModel.selectImage(it)
            findNavController().navigate(R.id.action_imageListFragment_to_imageDetailsFragment)
        }
        binding.photosGrid.adapter = adapter
        binding.search.setOnClickListener {

        }
    }
}