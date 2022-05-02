package com.gioia.radio

import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.gioia.radio.config.di
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.views.MainWindow
import org.kodein.di.compose.withDI
import org.kodein.di.instance

fun main(args: Array<String>) = application {
    if (args.any { it == "initDatabase" }) {
        val databaseGenerator: DatabaseGenerator by di.instance()
        databaseGenerator.generateDatabase()
        return@application
    }

    withDI(di) {
        Window(onCloseRequest = ::exitApplication) {
            MaterialTheme {
                MainWindow()
            }
        }
    }
}