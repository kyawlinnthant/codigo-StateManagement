package com.kyawlinnthant.codigo.statemanagementcodigo2.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Diet(
    val id: Int,
    val name: String,
    @SerialName("tool_tip")
    val tip: String
)
