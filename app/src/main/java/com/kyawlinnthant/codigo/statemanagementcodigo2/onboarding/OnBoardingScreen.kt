package com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Alcohol
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health
import com.kyawlinnthant.codigo.statemanagementcodigo2.screen.AllergyScreen
import com.kyawlinnthant.codigo.statemanagementcodigo2.screen.DietScreen
import com.kyawlinnthant.codigo.statemanagementcodigo2.screen.HealthScreen
import com.kyawlinnthant.codigo.statemanagementcodigo2.screen.StartScreen
import com.kyawlinnthant.codigo.statemanagementcodigo2.screen.VitaminScreen

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    modifier: Modifier = Modifier,
    paddingValues: PaddingValues,
    enabledExposure: Boolean,
    enabledSmoke: Boolean,
    alcohol: Alcohol,
    onAction: (OnBoardingAction) -> Unit,
    inputHealths: List<Health>,
    selectedDiets: List<Diet>,
    inputDiets: List<Diet>,
    selectedAllergies: List<Allergy>,
    inputAllergies: List<Allergy>,
    pagerState: PagerState,
    pages: List<OnBoardingScreens>
) {

    val backgroundColor = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.primaryContainer,
            MaterialTheme.colorScheme.primaryContainer
        )
    )

    val indicatorColor = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.secondary,
            MaterialTheme.colorScheme.secondary
        )
    )

    val indicatorFloat by remember {
        derivedStateOf {
            Animatable(pagerState.currentPage.toFloat() / pages.size.minus(1).toFloat())
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(paddingValues)
            .drawBehind {
                drawRect(
                    brush = backgroundColor
                )
            },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Bottom
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = modifier
                .fillMaxSize()
                .weight(1f),
            userScrollEnabled = false
        ) { page ->
            when (pages[page]) {
                OnBoardingScreens.Allergy -> AllergyScreen(
                    onAction = onAction,
                    inputAllergies = inputAllergies,
                    selectedAllergies = selectedAllergies
                )

                OnBoardingScreens.Diet -> DietScreen(
                    onAction = onAction,
                    inputDiets = inputDiets,
                    selectedDiets = selectedDiets
                )

                OnBoardingScreens.Health -> HealthScreen(
                    onAction = onAction,
                    inputHealths = inputHealths,
                )

                OnBoardingScreens.Start -> StartScreen(
                    onAction = onAction
                )

                OnBoardingScreens.Vitamin -> VitaminScreen(
                    onAction = onAction,
                    exposureEnabled = enabledExposure,
                    smokeEnabled = enabledSmoke,
                    alcohol = alcohol
                )
            }

        }

        Spacer(
            modifier = modifier
                .fillMaxWidth(fraction = indicatorFloat.value)
                .height(16.dp)
                .drawBehind {
                    drawRect(brush = indicatorColor)
                }
        )

    }

}