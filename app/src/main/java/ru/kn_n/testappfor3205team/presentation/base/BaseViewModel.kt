package ru.kn_n.testappfor3205team.presentation.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.kn_n.testappfor3205team.utils.Resource

abstract class BaseViewModel : ViewModel() {

    fun <T> requestWithLiveData(
        liveData: MutableLiveData<Resource<T>>,
        response: suspend () -> T
    ) {
        this.viewModelScope.launch(Dispatchers.IO) {
            liveData.postValue(Resource.loading(data = null))
            try {
                val data = response.invoke()
                liveData.postValue(Resource.success(data))
            } catch (e: Exception) {
                liveData.postValue(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
            }
        }
    }
}