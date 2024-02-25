package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

sealed interface OnBoardingScreens {
    data object Start : OnBoardingScreens
    data object Health : OnBoardingScreens
    data object Diet : OnBoardingScreens
    data object Allergy : OnBoardingScreens
    data object Vitamin : OnBoardingScreens
}