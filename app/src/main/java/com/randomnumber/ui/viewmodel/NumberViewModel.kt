package com.randomnumber.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.randomnumber.core.base.viewmodel.BaseViewModel
import com.randomnumber.core.data.network.models.ItemNumberModel
import com.randomnumber.core.data.repository.NumberRepository
import com.randomnumber.core.data.storage.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class NumberViewModel(private val repository: NumberRepository, mainDatabase: AppDatabase) :
    BaseViewModel() {

    private val database = mainDatabase.getItemNumberDAO()

    val aboutNumberLiveData = MutableLiveData<ItemNumberModel>()
    val savedItemsNumberLiveData = MutableLiveData<List<ItemNumberModel>>()

    fun getLocalData() = viewModelScope.launch(Dispatchers.IO) {
        val result = database.getBoundsEntities()
        if (result.isNotEmpty())
            savedItemsNumberLiveData.postValue(result.map { it.convertToLocalModel() })
    }

    fun getInfoAboutNumber(number: Int) = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getInfoAboundNumber(number)
        delay(1500)
        result.either(::handleFailure) {
            database.updateBoundsEntity(it.convertToLocalModel())
            aboutNumberLiveData.postValue(it)
        }
    }

    fun getRandomInfoAboutNumber() = viewModelScope.launch(Dispatchers.IO) {
        val result = repository.getRandomInfoAboundNumber()
        delay(1500)
        result.either(::handleFailure) {
            database.updateBoundsEntity(it.convertToLocalModel())
            aboutNumberLiveData.postValue(it)
        }
    }

}