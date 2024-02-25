package com.kyawlinnthant.codigo.statemanagementcodigo2.model

import kotlinx.serialization.Serializable

@Serializable
data class Allergies(
    val data: List<Allergy>,
)
