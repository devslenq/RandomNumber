package com.randomnumber.core.data.repository

import com.randomnumber.core.base.network.BaseRepository
import com.randomnumber.core.base.network.Failure
import com.randomnumber.core.base.network.Either
import com.randomnumber.core.data.network.Api
import com.randomnumber.core.data.network.models.ItemNumberModel

class NumberRepository(
    private val api: Api
): BaseRepository() {

    fun getInfoAboundNumber(number: Int): Either<Failure, ItemNumberModel> {
        return networkRequest(api.getInfoAboutNumber(number)::execute)
    }
    fun getRandomInfoAboundNumber(): Either<Failure, ItemNumberModel> {
        return networkRequest(api.getRandomInfoAboutNumber()::execute)
    }
}