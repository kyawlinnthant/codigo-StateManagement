package com.kyawlinnthant.codigo.statemanagementcodigo2.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.kyawlinnthant.codigo.statemanagementcodigo2.R

@Composable
fun HealthScreen(
    modifier: Modifier = Modifier,
    onGetStarted: () -> Unit
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
                text = stringResource(id = R.string.start_header),
                style = MaterialTheme.typography.displaySmall.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.start_desc1),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )
            Spacer(modifier = modifier.height(16.dp))
            Image(
                painter = painterResource(id = R.drawable.undraw_getting_coffee),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
            Spacer(modifier = modifier.height(16.dp))
            Text(
                text = stringResource(id = R.string.start_desc2),
                style = MaterialTheme.typography.titleLarge.copy(color = Color.Black),
                modifier = modifier.fillMaxWidth()
            )

        }

        Button(
            onClick = onGetStarted,
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(4.dp)
        ) {
            Text(text = "Get Started")
        }
    }
}