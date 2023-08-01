package pl.born2skill.imagefinder.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.born2skill.imagefinder.data.MatchedImage
import pl.born2skill.imagefinder.database.ImageDao
import pl.born2skill.imagefinder.repository.Repository
import java.lang.Exception

class ImageViewModel(imageDao: ImageDao) : ViewModel() {


    private val repository = Repository(imageDao)
    val photos: LiveData<List<MatchedImage>> = repository.images
    private val _selectedImage = MutableLiveData<MatchedImage>()
    val selectedImage: LiveData<MatchedImage> = _selectedImage

    init {
        getImageData("fruits")
    }

    fun getImageData(term: String){
        viewModelScope.launch {
            try {
                repository.storeResponse(term)
            } catch (e: Exception) {
                Log.d("Repository","store data in repo failed: $e")
            }
        }
    }

    fun selectImage(image: MatchedImage){
        _selectedImage.value = image
    }
}

class ImageViewModelFactory(private val imageDao: ImageDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ImageViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ImageViewModel(imageDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}