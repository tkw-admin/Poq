package com.tecknoworks.poq.data.mapper

import com.tecknoworks.poq.api.model.RepositoryDTO
import com.tecknoworks.poq.data.model.PoqRepository

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */
class PoqRepositoryMapper: Mapper<RepositoryDTO, PoqRepository> {

    override fun map(input: RepositoryDTO): PoqRepository {
        return PoqRepository(input.id, input.name, input.description)
    }

}