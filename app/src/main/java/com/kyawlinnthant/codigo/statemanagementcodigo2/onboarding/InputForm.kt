package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health

data class InputForm(
    val healths: List<Health> = emptyList(),
    val diets: List<Diet> = emptyList(),
    val allergies: List<Allergy> = emptyList()
) {

    fun healths() = healths
    fun diets() = diets
    fun allergies() = allergies
}
