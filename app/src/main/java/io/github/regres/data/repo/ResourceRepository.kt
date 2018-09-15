package io.github.regres.data.repo

import io.github.regres.data.entities.Resource
import io.github.regres.data.entities.Result
import io.github.regres.data.local.ResourceDao
import io.github.regres.data.remote.ReqresApi
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ResourceRepository @Inject constructor(
        private val reqresApi: ReqresApi,
        private val resourceDao: ResourceDao
) {
    fun getResources(): Observable<Result<Resource>> =
            reqresApi.getResources()
                    .doOnNext { Timber.d(it.data.toString()) }
                    .doOnError { Timber.e(it) }
}