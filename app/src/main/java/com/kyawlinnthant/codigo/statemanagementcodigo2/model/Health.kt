package com.kyawlinnthant.codigo.statemanagementcodigo2.model

import kotlinx.serialization.Serializable

@Serializable
data class Health(
    val id : Int,
    val name : String,
){
    fun toResult(priority : Int)= HealthResult(
        id = id,
        name = name,
        priority = priority
    )
}
