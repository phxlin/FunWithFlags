package me.yufanlin.funwithflags.dependencyInjection

import dagger.Component
import me.yufanlin.funwithflags.model.CountriesService
import me.yufanlin.funwithflags.viewmodel.ListViewModel

@Component(modules = [ApiModule::class])
interface ApiComponent {

    fun inject(service: CountriesService)
    fun inject(viewModel: ListViewModel)
}