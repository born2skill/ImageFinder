package pl.born2skill.imagefinder.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pl.born2skill.imagefinder.ImageApplication
import pl.born2skill.imagefinder.viewmodel.ImageViewModel
import pl.born2skill.imagefinder.viewmodel.ImageViewModelFactory
import pl.born2skill.imagefinder.R
import pl.born2skill.imagefinder.databinding.FragmentImageListBinding
import pl.born2skill.imagefinder.utils.replaceSpacesWithPlus

class ImageListFragment : Fragment() {

    private val viewModel: ImageViewModel by activityViewModels {
        ImageViewModelFactory(
            (activity?.application as ImageApplication).database.responseDao()
        )
    }
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
            showDetailsDialog()
        }
        binding.photosGrid.adapter = adapter
        binding.search.setOnClickListener {
            viewModel.getImageData(
                replaceSpacesWithPlus(binding.textInputEditText.text.toString())
            )
        }
    }

    private fun showDetailsDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.dialog_title))
            .setMessage(getString(R.string.dialog_message))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.dialog_negative)) { _, _ ->

            }
            .setPositiveButton(getString(R.string.dialog_positive)) { _, _ ->
                findNavController().navigate(R.id.action_imageListFragment_to_imageDetailsFragment)
            }
            .show()
    }
}