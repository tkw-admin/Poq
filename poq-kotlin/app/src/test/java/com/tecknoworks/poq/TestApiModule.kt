package com.tecknoworks.poq

import com.tecknoworks.poq.api.ApiModule
import com.tecknoworks.poq.api.RepoRequest
import io.mockk.mockk
import retrofit2.Retrofit

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
class TestApiModule: ApiModule() {

    override fun provideRetrofitService() : Retrofit = mockk()

    override fun provideRepoRequest(retrofit: Retrofit) : RepoRequest = mockk()

}