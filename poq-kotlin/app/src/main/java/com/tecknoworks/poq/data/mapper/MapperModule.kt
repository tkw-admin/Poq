package com.tecknoworks.poq.data.mapper

import dagger.Module
import dagger.Provides

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */

@Module
class MapperModule {

    @Provides
    fun providesMapper(): PoqRepositoryMapper {
        return PoqRepositoryMapper()
    }

}