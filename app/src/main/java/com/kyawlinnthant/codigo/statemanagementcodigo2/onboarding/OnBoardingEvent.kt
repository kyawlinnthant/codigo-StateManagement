package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

sealed interface OnBoardingEvent {
    data object ShowSnack : OnBoardingEvent
    data class OnNavigate(val screen : OnBoardingScreens) : OnBoardingEvent
}