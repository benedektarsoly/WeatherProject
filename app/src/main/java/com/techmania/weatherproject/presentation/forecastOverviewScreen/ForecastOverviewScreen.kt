package com.techmania.weatherproject.presentation.forecastOverviewScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techmania.weatherproject.R
import com.techmania.weatherproject.presentation.forecastOverviewScreen.forecastOverviewScreenComponents.ExpandableCard
import com.techmania.weatherproject.presentation.sharedComponents.ClimateInfoCardVertical
import com.techmania.weatherproject.presentation.sharedComponents.ImageWithShadow
import java.util.Locale

@Composable
fun ForecastOverViewScreen(
    forecastOverViewScreenViewModel: ForecastOverviewScreenViewModel = hiltViewModel(),
) {

    val weatherInfoDaily = forecastOverViewScreenViewModel.weatherInfoDaily.collectAsState()
    val cardStates = forecastOverViewScreenViewModel.cardStates.collectAsState()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            items(weatherInfoDaily.value.size) { index ->
                val weatherInfoSpecific = weatherInfoDaily.value[index]
                //refactor state outside of composable
                ExpandableCard(overView = {
                    Text(
                        text = weatherInfoSpecific.time.dayOfWeek.toString().lowercase()
                            .replaceFirstChar { it.titlecase(Locale.getDefault()) },
                        modifier = Modifier.weight(3f)
                    )
                    Spacer(modifier = Modifier.weight(2f))
                    Text(
                        text = "${weatherInfoSpecific.apparentTemperature}°",
                        modifier = Modifier.weight(1.5f)
                    )
                    ImageWithShadow(
                        imageResource = weatherInfoSpecific.iconRes,
                        contentDescription = weatherInfoSpecific.weatherDesc,
                        modifier = Modifier.weight(1.5f),
                        padding = 5.dp,
                        shadowColor = Color.DarkGray
                    )
                },
                    expandedState = cardStates.value[index],
                    onClick = {
                        forecastOverViewScreenViewModel.updateCardState(index)
                    }
                    , details = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ClimateInfoCardVertical(
                            imageResourceId = R.drawable.rainy,
                            textResourceId = R.string.rainfall,
                            amount = weatherInfoDaily.value[index].precipitation,
                            unitResourceId = R.string.unit_cm,
                            modifier = Modifier
                        )
                        ClimateInfoCardVertical(
                            imageResourceId = R.drawable.wind_direction,
                            textResourceId = R.string.wind,
                            amount = weatherInfoDaily.value[index].windSpeed,
                            unitResourceId = R.string.unit_kmh,
                            modifier = Modifier
                        )
                        ClimateInfoCardVertical(
                            imageResourceId = R.drawable.sunset,
                            textResourceId = R.string.apparent_temperature,
                            amount = weatherInfoDaily.value[index].apparentTemperature,
                            unitResourceId = R.string.unit_celsius,
                            modifier = Modifier
                        )
                    }
                })
            }
        }
    }
}