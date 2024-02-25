package com.kyawlinnthant.codigo.statemanagementcodigo2.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyawlinnthant.codigo.statemanagementcodigo2.R
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Diet
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingAction
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreens

@Composable
fun DietScreen(
    modifier: Modifier = Modifier,
    onAction: (OnBoardingAction) -> Unit,
    inputDiets: List<Diet>,
    selectedDiets: List<Diet>
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
                text = stringResource(id = R.string.diet_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            LazyColumn(modifier = modifier.fillMaxWidth()) {
                items(inputDiets.size) { index ->
                    val currentDiet = inputDiets[index]
                    val isSelected = selectedDiets.contains(currentDiet)
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(checked = isSelected, onCheckedChange = {
                            onAction(OnBoardingAction.AddDiet(diet = currentDiet))
                        })
                        Text(text = currentDiet.name, color = Color.Black)
                    }

                }
            }

        }
        Row(
            modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            TextButton(
                onClick = {
                    onAction(OnBoardingAction.Back(OnBoardingScreens.Diet))
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.back))
            }

            Button(
                onClick = {
                    onAction(OnBoardingAction.Next(OnBoardingScreens.Diet))
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}