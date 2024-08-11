package sheridan.caluagd.assignment4.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import sheridan.caluagd.assignment4.database.local.LocalMars
import sheridan.caluagd.assignment4.database.MarsApiService
import sheridan.caluagd.assignment4.database.local.MarsDao
import sheridan.caluagd.assignment4.model.Mars
import sheridan.caluagd.assignment4.database.MarsPhoto
import javax.inject.Inject
import javax.inject.Singleton

@OptIn(DelicateCoroutinesApi::class)
@Singleton
class MarsRepositoryImpl(
    private val marsApi: MarsApiService,
    private val marsDao: MarsDao,
    private val externalScope: CoroutineScope,
    private val dispatcher: CoroutineDispatcher,
): OfflineRepository{

    @Inject
    constructor(
        _marsApi: MarsApiService,
        _marsDao: MarsDao
    ): this(marsApi = _marsApi, marsDao = _marsDao, externalScope = GlobalScope, dispatcher = Dispatchers.IO)


    override fun getLocalPhotos(): Flow<List<Mars>> =
        marsDao.getLocalPhotos().map{list -> list.map {it.toModel()}}.flowOn(dispatcher)


    override suspend fun getPhotoById(int: Int): Mars? = withContext(dispatcher){
        marsDao.getPhotoById(int)?.toModel()
    }

    override suspend fun deleteLocalPhotos() {
        externalScope.launch(dispatcher) {
            marsDao.deleteList()
        }
    }

    override suspend fun updateLocalPhotos() {
        externalScope.launch(dispatcher){
            val photos : Deferred<List<LocalMars>> = async{
                marsApi.getPhotos().map { it.toLocal() }
            }
            marsDao.upsertMars(photos.await())
        }
    }


}

fun LocalMars.toModel(): Mars = Mars(id = this.id, imgSrc = imgSrc)

fun MarsPhoto.toLocal(): LocalMars = LocalMars(id = this.id, imgSrc = imgSrc)