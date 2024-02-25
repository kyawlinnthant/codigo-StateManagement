package com.kyawlinnthant.codigo.statemanagementcodigo2.model

import kotlinx.serialization.Serializable

@Serializable
data class Healths(
    val data: List<Health>,
)