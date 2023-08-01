package pl.born2skill.imagefinder.data

import pl.born2skill.imagefinder.database.Image

data class Response(
    val hits: List<MatchedImage>
)

fun Response.asDatabaseModel(): List<Image> {
    return hits.map {
        Image(
            previewURL = it.previewURL,
            largeImageURL = it.largeImageURL,
            user = it.user,
            tags = it.tags,
            downloads = it.downloads,
            likes = it.likes,
            comments = it.comments
        )
    }
}
