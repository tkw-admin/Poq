package com.tecknoworks.poq.api

import com.tecknoworks.poq.api.model.Repository
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */
interface RepoRequest {

    @GET("repos")
    suspend fun getRepos() : Response<List<Repository>>

}