package pl.born2skill.imagefinder.data

data class MatchedImage(
    val id: Int,
    val previewURL: String,
    val user: String,
    val tags: String,
    val downloads: Int,
    val likes: Int,
    val comments: Int,
    val largeImageURL: String
)
