package com.tecknoworks.poq.api.model

data class PermissionsDTO(
    val admin: Boolean,
    val pull: Boolean,
    val push: Boolean
)