package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Alcohol

data class FinalForm(
    val dailyExposure: Boolean = false,
    val smoke: Boolean = false,
    val alcohol: Alcohol = Alcohol.ZeroOne,
) {

    fun dailyExposure() = dailyExposure
    fun smoke() = smoke
    fun alcohol() = alcohol
}
