package com.tecknoworks.poq

import com.tecknoworks.poq.api.RepoRequest
import com.tecknoworks.poq.api.model.Repository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Mihai Ionescu on 2020-01-31.
 */

class AppRepositoryInjectTest {

    @Inject
    lateinit var repoRequest: RepoRequest

    @Before
    fun setup() {
        val component = DaggerTestAppComponent.builder()
            .apiModule(TestApiModule())
            .build()
        component.inject(this)
    }

    @Test
    fun testGetRepos() {
        Assert.assertNotNull(repoRequest)

        val repositoryMock = mockk<Repository>()

        // Given
        coEvery { repoRequest.getRepos() } returns Response.success(listOf(repositoryMock))

        // When
        val response = runBlocking { repoRequest.getRepos() }

        // Then
        Assert.assertNotNull(response)
        Assert.assertNotNull(response.body())
        Assert.assertNotNull(response.body()!![0])
    }

}