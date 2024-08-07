package sheridan.caluagd.assignment4

import android.app.Application
import sheridan.caluagd.assignment4.database.AppContainer

class MarsPhotoApplication : Application(){
    lateinit var container : AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }

}