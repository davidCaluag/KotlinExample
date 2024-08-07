package sheridan.caluagd.assignment4.database

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import sheridan.caluagd.assignment4.local.MarsApiService

interface AppContainer{
    val marsPhotosRepository: MarsPhotosRepository
}

class DefaultAppContainer : AppContainer {
    private val base_url = "https://android-kotlin-fun-mars-server.appspot.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(base_url)
        .build()


    private val retrofitService: MarsApiService by lazy {
        retrofit.create(MarsApiService::class.java)
    }

    /**
     * DI implementation for Mars photos repository
     */
    override val marsPhotosRepository: MarsPhotosRepository by lazy {
        NetworkMarsPhotosRepository(retrofitService)
    }
}