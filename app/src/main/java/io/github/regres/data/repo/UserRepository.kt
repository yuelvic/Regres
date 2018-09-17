package io.github.regres.data.repo

import io.github.regres.data.entities.Result
import io.github.regres.data.entities.User
import io.github.regres.data.local.UserDao
import io.github.regres.data.remote.ReqresApi
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
        private val reqresApi: ReqresApi,
        private val userDao: UserDao
) {
    fun getUsers(page: Int): Observable<Result<User>> =
            reqresApi.getUsers(page)
                    .doOnNext { Timber.d(it.data.toString()) }
                    .doOnError { Timber.e(it) }

    fun deleteUser(id: Int): Observable<String> =
            reqresApi.deleteUser(id)
                    .doOnNext { Timber.d(it) }
                    .doOnError { Timber.e(it) }
}