package com.kyawlinnthant.codigo.statemanagementcodigo2.screen

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyawlinnthant.codigo.statemanagementcodigo2.R
import com.kyawlinnthant.codigo.statemanagementcodigo2.model.Health
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingAction
import com.kyawlinnthant.codigo.statemanagementcodigo2.onboarding.OnBoardingScreens
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorder
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HealthScreen(
    modifier: Modifier = Modifier,
    onAction: (OnBoardingAction) -> Unit,
    inputHealths: List<Health>
) {

    var chosenItems = remember {
        mutableStateListOf<Health>()
    }
    val prioritizedState = rememberReorderableLazyListState(onMove = { from, to ->
        chosenItems = chosenItems.apply {
            add(to.index, removeAt(from.index))
        }
    })

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
                text = stringResource(id = R.string.health_title),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Text(
                text = stringResource(id = R.string.health_hint),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )

            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                modifier = modifier.fillMaxWidth()
            ) {
                inputHealths.forEach { currentHealth ->
                    val isSelected = chosenItems.contains(currentHealth)
                    FilterChip(
                        selected = isSelected,
                        onClick = {
                            if (isSelected) chosenItems.remove(currentHealth)
                            else chosenItems.add(currentHealth)
                        },
                        label = {
                            Text(
                                text = currentHealth.name,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        },
                        shape = CircleShape,
                        colors = FilterChipDefaults.filterChipColors(
                            selectedContainerColor = MaterialTheme.colorScheme.secondary
                        )
                    )

                }
            }

            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.health_prioritize),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            LazyColumn(
                modifier = modifier
                    .reorderable(prioritizedState)
                    .detectReorderAfterLongPress(prioritizedState),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                state = prioritizedState.listState
            ) {
                items(items = chosenItems, key = { it.id }) { item ->

                    ReorderableItem(prioritizedState, key = item) { isDragging ->
                        val elevation = animateDpAsState(
                            if (isDragging) 16.dp else 0.dp,
                            label = ""
                        )
                        Box(
                            modifier = modifier
                                .shadow(elevation.value)
                                .background(MaterialTheme.colorScheme.surface)
                        ) {
                            ListItem(
                                modifier = modifier
                                    .clip(RoundedCornerShape(8.dp))
                                    .border(
                                        BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
                                        shape = RoundedCornerShape(8.dp) // optional for rounded corners
                                    ),
                                headlineContent = {
                                    FilterChip(
                                        selected = true,
                                        onClick = {},
                                        label = {
                                            Text(
                                                text = item.name,
                                                color = Color.White
                                            )
                                        },
                                        shape = CircleShape,
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = MaterialTheme.colorScheme.secondary
                                        )
                                    )
                                },
                                colors = ListItemDefaults.colors(
                                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                                ),
                                trailingContent = {
                                    Icon(
                                        imageVector = Icons.Default.Menu,
                                        contentDescription = null,
                                        tint = MaterialTheme.colorScheme.secondary
                                    )
                                },
                                tonalElevation = 4.dp,

                                )
                        }
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
                    onAction(OnBoardingAction.Back(screen = OnBoardingScreens.Health))
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.back))
            }

            Button(
                onClick = {
                    onAction(OnBoardingAction.AddHealths(healths = chosenItems))
                    onAction(OnBoardingAction.Next(screen = OnBoardingScreens.Health))
                },
                shape = RoundedCornerShape(4.dp)
            ) {
                Text(text = stringResource(id = R.string.next))
            }
        }
    }
}