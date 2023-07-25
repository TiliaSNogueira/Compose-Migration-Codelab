/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.samples.apps.sunflower.plantdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.google.samples.apps.sunflower.R
import com.google.samples.apps.sunflower.data.Plant
import com.google.samples.apps.sunflower.viewmodels.PlantDetailViewModel

@Composable // Stateful: opinionated, easier to call from fragment
fun PlantDetailDescription(plantDetailViewModel: PlantDetailViewModel) {

    //observes values coming from VM's LiveData<Plant> field
    val plant by plantDetailViewModel.plant.observeAsState()

    plant?.let { plant ->
        PlantDetailContent(plant)
    }

}

@Composable // Statless : easier to preview and reusable
fun PlantDetailContent(plant: Plant) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(id = R.dimen.margin_normal))
    ) {
        PlantName(name = plant.name)
        PlantWatering(wateringInterval = plant.wateringInterval)
        //PlantDescription
    }
}

@Composable
fun PlantWatering(wateringInterval: Int) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = dimensionResource(id = R.dimen.margin_small))

    ) {
        Text(
            modifier = Modifier
                .padding(top = dimensionResource(id = R.dimen.margin_normal)),
            text = stringResource(id = R.string.watering_needs_prefix),
            color = MaterialTheme.colors.primaryVariant,
            fontWeight = FontWeight.Bold
        )

        val resources = LocalContext.current.resources
        val quantityString = resources.getQuantityString(
            R.plurals.watering_needs_suffix,
            wateringInterval, wateringInterval
        )
        Text(
            text = quantityString
        )
    }
}


@Composable
fun PlantName(name: String) {
    Text(
        text = name,
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = R.dimen.margin_small))
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally),
        style = MaterialTheme.typography.h5
    )
}

@Preview
@Composable
fun PlantDetailDescriptionPreview() {
    MaterialTheme {
        val plant = Plant("id", "Apple", "description", 3, 30, "")
        PlantDetailContent(plant = plant)
    }
}

@Preview
@Composable
fun PlantNamePreview() {
    MaterialTheme {
        PlantName(name = "Tília")
    }
}
