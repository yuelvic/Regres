package io.github.regres.ui.main

import androidx.lifecycle.MutableLiveData
import io.github.regres.data.entities.Resource
import io.github.regres.data.entities.Result
import io.github.regres.data.entities.User
import io.github.regres.data.repo.ResourceRepository
import io.github.regres.data.repo.UserRepository
import io.github.regres.ui.base.BaseViewModel
import io.github.regres.utils.extensions.Data
import io.github.regres.utils.extensions.DataState
import io.github.regres.utils.extensions.performOnMain
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(
        private val resourceRepository: ResourceRepository,
        private val userRepository: UserRepository
): BaseViewModel() {
    private val resourceLiveData = MutableLiveData<Data<Result<Resource>>>()

    private lateinit var resources: Result<Resource>
    private lateinit var users: List<User>

    private fun combine() {
        Timber.d(resources.data.toString())
        for (resource in resources.data!!)
            resource.users = users
        resourceLiveData.postValue(Data(dataState = DataState.SUCCESS, data = resources))
    }

    fun get(): MutableLiveData<Data<Result<Resource>>> {
        compositeDisposable.add(userRepository.getUsers(1)
                .flatMap {
                    this.users = it.data!!
                    return@flatMap resourceRepository.getResources(1)
                }
                .doOnSubscribe { resourceLiveData.postValue(Data(dataState = DataState.LOADING, data = null)) }
                .performOnMain()
                .subscribe({
                    this.resources = it
                    combine()
                }, {
                    Timber.e(it)
                    resourceLiveData.postValue(Data(dataState = DataState.ERROR, data = null))
                }))
        return this.resourceLiveData
    }

    fun get(page: Int): MutableLiveData<Data<Result<Resource>>> {
        compositeDisposable.add(resourceRepository.getResources(page)
                .doOnSubscribe { resourceLiveData.postValue(Data(dataState = DataState.LOADING, data = null)) }
                .performOnMain()
                .subscribe({
                    if (it.data!!.isNotEmpty()) {
                        this.resources = it
                        combine()
                    } else {
                        resourceLiveData.postValue(Data(dataState = DataState.SUCCESS, data = null))
                    }
                }, {
                    Timber.e(it)
                    resourceLiveData.postValue(Data(dataState = DataState.ERROR, data = null))
                }))
        return this.resourceLiveData
    }
}