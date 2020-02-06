package com.tecknoworks.poq.data

import com.tecknoworks.poq.ActivityScope
import com.tecknoworks.poq.MainActivity
import com.tecknoworks.poq.ui.main.MainFragment
import dagger.Subcomponent

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
@ActivityScope
@Subcomponent
interface RepositoriesComponent {

    @Subcomponent.Builder
    interface Builder {
        fun build() : RepositoriesComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(mainFragment: MainFragment)

}