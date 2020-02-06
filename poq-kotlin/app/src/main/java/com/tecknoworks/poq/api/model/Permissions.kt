package com.tecknoworks.poq.api.model

data class Permissions(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)