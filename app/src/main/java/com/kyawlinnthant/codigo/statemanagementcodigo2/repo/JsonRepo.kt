package com.kyawlinnthant.codigo.statemanagementcodigo2.repo

import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Vitamin
import kotlinx.serialization.json.JsonElement

interface JsonRepo {

    suspend fun getHealths() : List<Health>
    suspend fun getDiets() : List<Diet>
    suspend fun getAllergies() : List<Allergy>
    suspend fun getResult(vitamin: Vitamin) : JsonElement

}