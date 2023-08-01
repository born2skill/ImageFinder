package pl.born2skill.imagefinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import pl.born2skill.imagefinder.databinding.FragmentImageDetailsBinding

class ImageDetailsFragment : Fragment() {

    private val viewModel: ImageViewModel by activityViewModels {
        ImageViewModelFactory(
            (activity?.application as ImageApplication).database.responseDao()
        )
    }
    private var _binding: FragmentImageDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }
}