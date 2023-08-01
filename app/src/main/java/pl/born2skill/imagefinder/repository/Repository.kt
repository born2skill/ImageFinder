package pl.born2skill.imagefinder.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.map
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import pl.born2skill.imagefinder.data.MatchedImage
import pl.born2skill.imagefinder.data.asDatabaseModel
import pl.born2skill.imagefinder.database.ImageDao
import pl.born2skill.imagefinder.database.asDataObject
import pl.born2skill.imagefinder.network.ImageApi

class Repository(private val dao: ImageDao) {

    val images: LiveData<List<MatchedImage>> = dao.getImages().asLiveData().map {
        it.asDataObject()
    }

    suspend fun storeResponse(phrase: String) {
        withContext(Dispatchers.IO) {
            val images = ImageApi.retrofitService.getImages(
                term = phrase
            )
            dao.insert(images.asDatabaseModel())
        }
    }
}