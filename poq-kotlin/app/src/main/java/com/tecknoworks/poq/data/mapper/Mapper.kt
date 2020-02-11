package com.tecknoworks.poq.data.mapper

/**
 * Created by Mihai Ionescu on 2020-02-11.
 */
interface Mapper<I, O> {
    fun map(input: I): O
}