package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

sealed class OnBoardingScreens(val index: Int) {
    data object Start : OnBoardingScreens(0)
    data object Health : OnBoardingScreens(1)
    data object Diet : OnBoardingScreens(2)
    data object Allergy : OnBoardingScreens(3)
    data object Vitamin : OnBoardingScreens(4)
}