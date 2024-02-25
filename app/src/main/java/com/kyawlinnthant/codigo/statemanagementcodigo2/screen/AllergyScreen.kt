package com.kyawlinnthant.codigo.statemanagementcodigo2.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyawlinnthant.codigo.statemanagementcodigo2.R
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Allergy
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingAction
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreens

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AllergyScreen(
    modifier: Modifier = Modifier,
    onAction: (OnBoardingAction) -> Unit,
    inputAllergies: List<Allergy>,
    selectedAllergies: List<Allergy>,
) {
    val typedText = remember {
        mutableStateOf("")
    }
    val suggestedItem by remember {
        derivedStateOf {
            inputAllergies.filter {

                if (typedText.value.isEmpty())
                    false else
                    it.name.contains(typedText.value)
            }
        }
    }
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
                text = stringResource(id = R.string.allergy_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(16.dp))


            OutlinedTextField(
                value = typedText.value,
                onValueChange = { typedText.value = it },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = MaterialTheme.colorScheme.secondary,
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                ),
                modifier = modifier.fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {
                    FlowRow(
                        horizontalArrangement = Arrangement.spacedBy(2.dp),
                        verticalArrangement = Arrangement.spacedBy(1.dp),
                        modifier = modifier.wrapContentWidth(
                            align = Alignment.Start
                        ),
                        maxItemsInEachRow = 2,
                    ) {
                        selectedAllergies.forEach { current ->
                            FilterChip(
                                selected = true,
                                onClick = {},
                                label = {
                                    Text(
                                        text = current.name,
                                        color = Color.White
                                    )
                                },
                                shape = CircleShape,
                                colors = FilterChipDefaults.filterChipColors(
                                    selectedContainerColor = MaterialTheme.colorScheme.secondary
                                )
                            )

                        }
                    }
                }
            )

            LazyColumn(
                modifier = modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)

            ) {
                items(suggestedItem.size) { index ->
                    val current = suggestedItem[index]
                    ListItem(
                        headlineContent = {
                            Text(text = current.name, color = Color.Black)
                        },
                        colors = ListItemDefaults.colors(
                            containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        ),
                        modifier = modifier.clickable {
                            onAction(OnBoardingAction.AddAllergy(current))
                            typedText.value = ""
                        }

                    )
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
                    onAction(OnBoardingAction.Back(OnBoardingScreens.Allergy))
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.back))
            }

            Button(
                onClick = {
                    onAction(OnBoardingAction.Next(OnBoardingScreens.Allergy))
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}
