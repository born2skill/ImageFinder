package pl.born2skill.imagefinder

import android.app.Application
import pl.born2skill.imagefinder.database.ImageDatabase

class ImageApplication : Application() {
    val database: ImageDatabase by lazy { ImageDatabase.getDatabase(this) }
}