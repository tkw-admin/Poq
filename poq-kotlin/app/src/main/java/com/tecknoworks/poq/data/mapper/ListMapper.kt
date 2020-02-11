package com.tecknoworks.poq.data.mapper

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */
class ListMapper<I, O> (
    private val mapper: Mapper<I, O>
) : Mapper<List<I>, List<O>> {
    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}