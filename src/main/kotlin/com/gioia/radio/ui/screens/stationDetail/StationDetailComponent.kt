package com.gioia.radio.ui.screens.stationDetail

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.ComponentContext
import com.gioia.radio.data.domains.RadioStation
import com.gioia.radio.ui.navigation.Component

class StationDetailComponent(
    private val componentContext: ComponentContext,
    private val selectedStation: RadioStation,
    private val onFinished: () -> Unit
) : Component, ComponentContext by componentContext {
    @Composable
    override fun render() {
        StationDetail(
            selectedStation,
            onFinished
        )
    }
}