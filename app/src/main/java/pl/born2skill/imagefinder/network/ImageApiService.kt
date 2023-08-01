package pl.born2skill.imagefinder.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import pl.born2skill.imagefinder.data.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pixabay.com/"
private const val APIKEY = "38555203-4d74d9571401bb1306450e904"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val httpClient = OkHttpClient.Builder()
    .addInterceptor(LoggingInterceptor())
    .build()

private val retrofit = Retrofit.Builder()
    .client(httpClient)
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ImageApiService {
    @GET("api")
    suspend fun getImages(
        @Query("key") apikey: String = APIKEY,
        @Query("q") term: String
    ): Response
}

object ImageApi {
    val retrofitService : ImageApiService by lazy {
        retrofit.create(ImageApiService::class.java)
    }
}