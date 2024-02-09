package com.randomnumber.di


import com.randomnumber.core.data.network.intercept.HeaderModifierInterceptor
import com.randomnumber.core.data.repository.NumberRepository
import com.randomnumber.ui.viewmodel.NumberViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val networkModules = module {
    singleOf(::provideRetrofit)
    singleOf(::provideApi)
    singleOf(::NumberRepository)
    singleOf(::HeaderModifierInterceptor)
    singleOf(::provideClient)
}

private val viewmodelModules = module {
    viewModelOf(::NumberViewModel)
}

private val applicationModules = module {
    singleOf(::provideAppDataDatabase)
}

fun provideAllModules() = listOf(networkModules, applicationModules, viewmodelModules)