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

    fun addUser(user: User): MutableLiveData<Data<User>> {
        compositeDisposable.add(userRepository.addUser(user)
                .doOnSubscribe { addUserLiveData.postValue(Data(dataState = DataState.LOADING)) }
                .performOnMain()
                .subscribe({
                    addUserLiveData.postValue(Data(dataState = DataState.SUCCESS, data = it))
                }, {
                    addUserLiveData.postValue(Data(dataState = DataState.ERROR))
                })
        )
        return this.addUserLiveData
    }

    fun deleteUser(id: Int): MutableLiveData<Data<String>> {
        userRepository.deleteUser(id)
                .doOnSubscribe {
                    deleteUserLiveData.postValue(Data(dataState = DataState.LOADING))
                }
                .subscribe({
                    deleteUserLiveData.postValue(Data(dataState = DataState.SUCCESS))
                }, {
                    deleteUserLiveData.postValue(Data(dataState = DataState.ERROR))
                    Timber.e(it)
                }).dispose()
        return this.deleteUserLiveData
    }
}