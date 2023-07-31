package pl.born2skill.imagefinder

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.born2skill.imagefinder.data.MatchedImage
import pl.born2skill.imagefinder.data.Response
import pl.born2skill.imagefinder.network.ImageApi
import java.lang.Exception

class ImageViewModel : ViewModel() {

    private val _response = MutableLiveData<Response>()
    private val response: LiveData<Response> = _response
    val photos: LiveData<List<MatchedImage>> = response.map {response ->
        response.hits
    }
    private val _selectedImage = MutableLiveData<MatchedImage>()
    val selectedImage: LiveData<MatchedImage> = _selectedImage

    init {
        getMatchedImages("fruits")
    }

    fun getMatchedImages(term: String){
        viewModelScope.launch {
            try {
                _response.value = ImageApi.retrofitService.getImages(
                    term = term
                )
                Log.d("pyklo","chyba")
            } catch (e: Exception) {
                //_response.value
                Log.d("pyklo","chyba nie: $e")
            }
        }
    }

    fun selectImage(image: MatchedImage){
        _selectedImage.value = image
    }
}