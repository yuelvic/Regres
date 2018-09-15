package io.github.regres.ui.main

import androidx.lifecycle.MutableLiveData
import io.github.regres.data.entities.Resource
import io.github.regres.data.entities.Result
import io.github.regres.data.repo.ResourceRepository
import io.github.regres.ui.base.BaseViewModel
import io.github.regres.utils.extensions.Data
import io.github.regres.utils.extensions.DataState
import io.github.regres.utils.extensions.performOnMain
import timber.log.Timber
import javax.inject.Inject

class MainViewModel @Inject constructor(private val resourceRepository: ResourceRepository): BaseViewModel() {
    private val resourceLiveData = MutableLiveData<Data<Result<Resource>>>()

    fun getResources(): MutableLiveData<Data<Result<Resource>>> {
        compositeDisposable.add(resourceRepository.getResources()
                .performOnMain()
                .subscribe({
                    resourceLiveData.postValue(Data(dataState = DataState.SUCCESS, data = it))
                }, {
                    Timber.e(it)
                    resourceLiveData.postValue(Data(dataState = DataState.ERROR, data = null))
                }, {
                    resourceLiveData.postValue(Data(dataState = DataState.LOADING, data = null))
                }))
        return this.resourceLiveData
    }
}