package com.kyawlinnthant.codigo.statemanagementcodigo2.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Vitamin(
    @SerialName("health_concerns")
    val healths: List<HealthResult>,
    @SerialName("diets")
    val diets: List<Diet>,
    @SerialName("is_daily_exposure")
    val isDailyExposure: Boolean,
    @SerialName("is_somke")
    val isSmoke: Boolean,
    @SerialName("alchol")
    val alcohol: String,
    @SerialName("allergies")
    val allergies: List<Allergy>
)

@Serializable
data class HealthResult(
    val id: Int,
    val name: String,
    val priority: Int
)
