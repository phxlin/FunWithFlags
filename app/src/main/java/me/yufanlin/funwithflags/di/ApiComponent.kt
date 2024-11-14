package me.yufanlin.funwithflags.di

import dagger.Component
import me.yufanlin.funwithflags.data.remote.CountriesService
import me.yufanlin.funwithflags.ui.viewmodel.ListViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}