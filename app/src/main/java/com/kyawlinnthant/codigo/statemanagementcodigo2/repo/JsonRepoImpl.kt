package com.kyawlinnthant.codigo.statemanagementcodigo2.repo

import android.content.Context
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergies
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diets
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Healths
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Vitamin
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import javax.inject.Inject

class JsonRepoImpl @Inject constructor(
    private val json: Json,
    @ApplicationContext private val context: Context
) : JsonRepo {
    override suspend fun getHealths(): List<Health> {
        val filename = "healths.json"
        val jsonString = getJsonFile(filename)
        val healths =
            json.decodeFromString(deserializer = Healths.serializer(), string = jsonString)
        return healths.data
    }

    override suspend fun getDiets(): List<Diet> {
        val filename = "diets.json"
        val jsonString = getJsonFile(filename)
        val diets = json.decodeFromString(deserializer = Diets.serializer(), string = jsonString)
        return diets.data
    }

    override suspend fun getAllergies(): List<Allergy> {
        val filename = "allergies.json"
        val jsonString = getJsonFile(filename)
        val allergies =
            json.decodeFromString(deserializer = Allergies.serializer(), string = jsonString)
        return allergies.data
    }

    override suspend fun getResult(vitamin: Vitamin): JsonElement {
        return json.encodeToJsonElement(serializer = Vitamin.serializer(), value = vitamin)
    }

    private fun getJsonFile(name: String): String {
        val inputStream = context.assets.open(name)
        return inputStream.bufferedReader().use { it.readText() }.also {
            inputStream.close()
        }
    }
}