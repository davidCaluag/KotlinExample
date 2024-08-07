package sheridan.caluagd.assignment4

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import sheridan.caluagd.assignment4.database.AppContainer
import sheridan.caluagd.assignment4.database.DefaultAppContainer


@HiltAndroidApp
class MarsPhotoApplication : Application(){
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}