package com.kyawlinnthant.codigo.statemanagementcodigo2.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyawlinnthant.codigo.statemanagementcodigo2.R
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Alcohol
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingAction
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreens

@Composable
fun VitaminScreen(
    modifier: Modifier = Modifier,
    onAction: (OnBoardingAction) -> Unit,
    exposureEnabled: Boolean,
    smokeEnabled: Boolean,
    alcohol: Alcohol,
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.Bottom,
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .weight(1f)
                .systemBarsPadding()
        ) {
            Text(
                text = stringResource(id = R.string.daily_exposure),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = exposureEnabled, onClick = {
                    onAction(OnBoardingAction.SetDailyExposure(true))
                })
                Text(text = stringResource(id = R.string.yes), color = Color.Black)
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = !exposureEnabled, onClick = {
                    onAction(OnBoardingAction.SetDailyExposure(false))
                })
                Text(text = stringResource(id = R.string.no), color = Color.Black)
            }
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.smoke),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = smokeEnabled, onClick = {
                    onAction(OnBoardingAction.SetSmoke(true))
                })
                Text(text = stringResource(id = R.string.yes), color = Color.Black)
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = !smokeEnabled, onClick = {
                    onAction(OnBoardingAction.SetSmoke(false))
                })
                Text(text = stringResource(id = R.string.no), color = Color.Black)
            }
            Spacer(modifier = modifier.height(16.dp))

            Text(
                text = stringResource(id = R.string.alcohol),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = alcohol is Alcohol.ZeroOne, onClick = {
                    onAction(OnBoardingAction.SetAlcohol(Alcohol.ZeroOne))
                })
                Text(text = Alcohol.ZeroOne.value, color = Color.Black)
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = alcohol is Alcohol.TwoFive, onClick = {
                    onAction(OnBoardingAction.SetAlcohol(Alcohol.TwoFive))
                })
                Text(text = Alcohol.TwoFive.value, color = Color.Black)
            }
            Row(
                modifier = modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(selected = alcohol is Alcohol.FivePlus, onClick = {
                    onAction(OnBoardingAction.SetAlcohol(Alcohol.FivePlus))
                })
                Text(text = Alcohol.FivePlus.value, color = Color.Black)
            }

        }

        Button(
            onClick = {
                onAction(OnBoardingAction.Next(OnBoardingScreens.Vitamin))
            },
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = stringResource(id = R.string.get))
        }
    }
}