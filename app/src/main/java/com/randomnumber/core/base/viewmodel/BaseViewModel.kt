package com.randomnumber.core.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.randomnumber.core.base.network.Failure

abstract class BaseViewModel: ViewModel() {
    val failure: MutableLiveData<Failure> = MutableLiveData()

    fun handleFailure(failure: Failure) {
        this.failure.postValue(failure)
    }
}