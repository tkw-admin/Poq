package com.tecknoworks.poq

import com.tecknoworks.poq.api.ApiModule
import com.tecknoworks.poq.data.RepositoriesComponent
import com.tecknoworks.poq.data.RepositoriesModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
@Singleton
@Component(modules = [ApiModule::class, RepositoriesModule::class])
interface PoqApplicationComponent {

    fun repositoriesComponent() : RepositoriesComponent.Builder

}