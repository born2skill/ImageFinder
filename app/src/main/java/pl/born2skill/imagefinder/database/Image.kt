package pl.born2skill.imagefinder.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import pl.born2skill.imagefinder.data.MatchedImage

@Entity(tableName = "image")
data class Image(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "preview_url")
    val previewURL: String,
    @ColumnInfo(name = "large_image_url")
    val largeImageURL: String,
    @ColumnInfo(name = "user")
    val user: String,
    @ColumnInfo(name = "tags")
    val tags: String,
    @ColumnInfo(name = "downloads")
    val downloads: Int,
    @ColumnInfo(name = "likes")
    val likes: Int,
    @ColumnInfo(name = "comments")
    val comments: Int,
)

fun List<Image>.asDataObject(): List<MatchedImage> {
    return map {
        MatchedImage(
            id = it.id,
            previewURL = it.previewURL,
            user = it.user,
            tags = it.tags,
            downloads = it.downloads,
            likes = it.likes,
            comments = it.comments,
            largeImageURL = it.largeImageURL
        )
    }
}
