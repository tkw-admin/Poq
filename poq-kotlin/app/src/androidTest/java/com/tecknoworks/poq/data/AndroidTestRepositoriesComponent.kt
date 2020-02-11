package com.tecknoworks.poq.data

import com.tecknoworks.poq.ActivityScope
import com.tecknoworks.poq.data.mapper.MapperModule
import com.tecknoworks.poq.ui.main.MainFragmentTest
import dagger.Subcomponent

/**
 * Created by Mihai Ionescu on 2020-02-03.
 */

@ActivityScope
@Subcomponent(modules = [MapperModule::class])
interface AndroidTestRepositoriesComponent: RepositoriesComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build() : AndroidTestRepositoriesComponent
    }

    fun inject(mainFragmentTest: MainFragmentTest)
}