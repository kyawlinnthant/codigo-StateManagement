package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Alcohol
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health

sealed interface OnBoardingAction {
    data class AddHealth(val health: Health) : OnBoardingAction
    data class RemoveHealth(val health: Health) : OnBoardingAction
    data class AddDiet(val diet: Diet) : OnBoardingAction
    data class RemoveDiet(val diet: Diet) : OnBoardingAction
    data class AddAllergy(val allergy: Allergy) : OnBoardingAction
    data class SetDailyExposure(val enabled: Boolean) : OnBoardingAction
    data class SetSmoke(val enabled: Boolean) : OnBoardingAction
    data class SetAlcohol(val alcohol: Alcohol) : OnBoardingAction

    data class Next(val screen: OnBoardingScreens) : OnBoardingAction
    data class Back(val screen: OnBoardingScreens) : OnBoardingAction
}