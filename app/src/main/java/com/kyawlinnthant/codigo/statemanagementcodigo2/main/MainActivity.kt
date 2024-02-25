package com.kyawlinnthant.codigo.statemanagementcodigo2.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreen
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingViewModel
import com.kyawlinnthant.codigo.statemanagementcodigo2.theme.StateManagementCodigo2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val vm: OnBoardingViewModel = hiltViewModel()
            val selectedHealths = vm.selectedHealths
            val selectedDiets = vm.selectedDiets
            val selectedAllergies = vm.selectedAllergies
            val dailyExposure = vm.dailyExposure.collectAsState()
            val smoke = vm.smoke.collectAsState()
            val alcohol = vm.alcohol.collectAsState()

            val supportedHealths = vm.healths.collectAsState()
            val supportedDiets = vm.diets.collectAsState()
            val supportedAllergies = vm.allergies.collectAsState()

            LaunchedEffect(Unit) {
                vm.getInputForms()
            }

            StateManagementCodigo2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        contentWindowInsets = WindowInsets(0,0,0,0)
                    ) {
                        OnBoardingScreen(
                            paddingValues = it
                        )
                    }
                }
            }
        }
    }
}
