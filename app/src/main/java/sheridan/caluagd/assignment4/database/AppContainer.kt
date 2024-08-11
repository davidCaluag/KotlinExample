package sheridan.caluagd.assignment4.database

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object DefaultAppContainer {
    private val base_url = "https://android-kotlin-fun-mars-server.appspot.com/"


    @Provides
    @Singleton
    fun retrofit(): Retrofit =
        Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(base_url)
        .build()
    /**
     * DI implementation for Mars photos repository
     */


    @Singleton
    @Provides
    fun provideApi(retrofit: Retrofit): MarsApiService {
        return retrofit.create(MarsApiService::class.java)
    }

}