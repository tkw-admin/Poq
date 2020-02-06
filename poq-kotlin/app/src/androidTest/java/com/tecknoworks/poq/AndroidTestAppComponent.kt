package com.tecknoworks.poq

import com.tecknoworks.poq.api.ApiModule
import com.tecknoworks.poq.data.AndroidTestRepositoriesComponent
import com.tecknoworks.poq.data.RepositoriesComponent
import com.tecknoworks.poq.data.RepositoriesModule
import com.tecknoworks.poq.ui.main.MainFragmentTest
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */

@Singleton
@Component(modules = [ApiModule::class])
interface AndroidTestAppComponent : PoqApplicationComponent {
    fun androidTestRepositoriesComponent() : AndroidTestRepositoriesComponent.Builder
}