package com.tecknoworks.poq.data.repository

import com.tecknoworks.poq.api.RepoRequest
import com.tecknoworks.poq.api.model.RepositoryDTO
import com.tecknoworks.poq.data.PoqResult
import com.tecknoworks.poq.data.mapper.PoqRepositoryMapper
import com.tecknoworks.poq.data.model.PoqRepository
import java.lang.Exception
import javax.inject.Inject

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */

// Lack of name creativity
class PoqRepositoryRepository @Inject constructor (
    private val repoRequest: RepoRequest,
    private val poqRepositoryMapper: PoqRepositoryMapper
) {

    suspend fun getRepositories(): PoqResult<List<PoqRepository>> {
        var poqResult: PoqResult<List<PoqRepository>>
        try {
            val repositoryListResponse = repoRequest.getRepos()
            if (repositoryListResponse.isSuccessful) {
                poqResult = PoqResult.Success(mapRepository(repositoryListResponse.body()))
            } else {
                poqResult = PoqResult.Failure(Exception(repositoryListResponse.message()))
            }
        } catch (e : Exception) {
            poqResult = PoqResult.Failure(e)
        }
        return poqResult
    }

    private fun mapRepository(repositoryDTOs: List<RepositoryDTO>?): List<PoqRepository> {
        if (repositoryDTOs == null) {
            return emptyList()
        }
        return repositoryDTOs.map {
            poqRepositoryMapper.map(it)
        }
    }

}