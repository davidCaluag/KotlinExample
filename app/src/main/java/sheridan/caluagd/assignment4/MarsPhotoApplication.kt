package sheridan.caluagd.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import sheridan.caluagd.assignment4.repository.OfflineRepository
import javax.inject.Inject


@HiltAndroidApp
class MarsPhotoApplication() : Application(){

    @Inject
    lateinit var container : OfflineRepository

    override fun onCreate() {
        super.onCreate()

        MainScope().launch {
            container.updateLocalPhotos()
        }
    }

}