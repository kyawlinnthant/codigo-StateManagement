package com.kyawlinnthant.codigo.statemanagementcodigo2.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.kyawlinnthant.codigo.statemanagementcodigo2.R
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingEvent
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreen
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreens
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingViewModel
import com.kyawlinnthant.codigo.statemanagementcodigo2.theme.StateManagementCodigo2Theme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            val vm: OnBoardingViewModel = hiltViewModel()
            val selectedDiets = vm.selectedDiets
            val selectedAllergies = vm.selectedAllergies
            val dailyExposure = vm.dailyExposure.collectAsState()
            val smoke = vm.smoke.collectAsState()
            val alcohol = vm.alcohol.collectAsState()

            val supportedHealths = vm.healths.collectAsState()
            val supportedDiets = vm.diets.collectAsState()
            val supportedAllergies = vm.allergies.collectAsState()
            val snackState = remember { SnackbarHostState() }

            val error = stringResource(id = R.string.error)

            val pages = listOf(
                OnBoardingScreens.Start,
                OnBoardingScreens.Health,
                OnBoardingScreens.Diet,
                OnBoardingScreens.Allergy,
                OnBoardingScreens.Vitamin

            )
            val pagerState = rememberPagerState(
                initialPage = 0,
                pageCount = { pages.size }
            )
            LaunchedEffect(Unit) {
                vm.getInputForms()
                vm.uiEvent.collect {
                    when (it) {
                        OnBoardingEvent.ShowSnack -> snackState.showSnackbar(error)
                        is OnBoardingEvent.OnNavigate -> pagerState.animateScrollToPage(page = it.screen.index)
                    }
                }
            }


            StateManagementCodigo2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { SnackbarHost(hostState = snackState) },
                        contentWindowInsets = WindowInsets(0, 0, 0, 0)
                    ) {
                        OnBoardingScreen(
                            paddingValues = it,
                            enabledExposure = dailyExposure.value,
                            enabledSmoke = smoke.value,
                            alcohol = alcohol.value,
                            inputHealths = supportedHealths.value,
                            selectedDiets = selectedDiets,
                            inputDiets = supportedDiets.value,
                            selectedAllergies = selectedAllergies,
                            inputAllergies = supportedAllergies.value,
                            onAction = vm::onAction,
                            pagerState = pagerState,
                            pages = pages
                        )
                    }
                }
            }
        }
    }
}
