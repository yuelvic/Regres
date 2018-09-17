package io.github.regres.ui.user

import androidx.lifecycle.MutableLiveData
import io.github.regres.data.entities.User
import io.github.regres.data.repo.UserRepository
import io.github.regres.ui.base.BaseViewModel
import io.github.regres.utils.extensions.Data
import io.github.regres.utils.extensions.DataState
import io.github.regres.utils.extensions.performOnMain
import timber.log.Timber
import javax.inject.Inject

class UserViewModel @Inject constructor(
        private val userRepository: UserRepository
): BaseViewModel() {
    val ADD = "ADD"
    val DELETE = "DELETE"

    private val addUserLiveData = MutableLiveData<Data<User>>()
    private val deleteUserLiveData = MutableLiveData<Data<String>>()

    fun deleteUser(id: Int): MutableLiveData<Data<String>> {
        compositeDisposable.add(userRepository.deleteUser(id)
                .doOnSubscribe {
                    deleteUserLiveData.postValue(Data(dataState = DataState.LOADING))
                }
                .performOnMain()
                .subscribe({
                    deleteUserLiveData.postValue(Data(dataState = DataState.SUCCESS))
                }, {
                    deleteUserLiveData.postValue(Data(dataState = DataState.ERROR))
                    Timber.e(it)
                }))
        return this.deleteUserLiveData
    }
}