package com.kyawlinnthant.codigo.statemanagementcodigo2.model

import kotlinx.serialization.Serializable

@Serializable
data class Diets(
    val data: List<Diet>,
)