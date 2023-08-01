package pl.born2skill.imagefinder.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(images: List<Image>)

    @Query("SELECT * FROM image")
    fun getImages(): Flow<List<Image>>

    @Query("DELETE FROM image")
    suspend fun deleteAll()
}