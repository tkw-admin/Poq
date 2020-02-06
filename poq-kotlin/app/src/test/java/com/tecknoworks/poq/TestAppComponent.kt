package com.tecknoworks.poq

import com.tecknoworks.poq.api.ApiModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */

@Singleton
@Component(modules = [ApiModule::class])
interface TestAppComponent : PoqApplicationComponent {
    fun inject(appRepositoryInjectTest: AppRepositoryInjectTest)
}