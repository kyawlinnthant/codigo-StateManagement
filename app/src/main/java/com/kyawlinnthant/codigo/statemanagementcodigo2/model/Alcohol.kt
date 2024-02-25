package com.kyawlinnthant.codigo.statemanagementcodigo2.model

sealed class Alcohol(val value: String) {
    data object ZeroOne : Alcohol("0 - 1")
    data object TwoFive : Alcohol("2 - 5")
    data object FivePlus : Alcohol("5+")
}
