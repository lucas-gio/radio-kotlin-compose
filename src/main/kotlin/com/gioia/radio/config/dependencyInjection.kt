package com.gioia.radio.config

import com.gioia.radio.data.repositories.CountryRepository
import com.gioia.radio.data.repositories.CountryRepositoryImpl
import com.gioia.radio.services.PlayerService
import com.gioia.radio.services.PlayerServiceImpl
import com.gioia.radio.tools.DatabaseGenerator
import com.gioia.radio.tools.DatabaseGeneratorImpl
import com.gioia.radio.views.viewModels.StationsViewModel
import org.dizitart.no2.Nitrite
import org.kodein.di.DI
import org.kodein.di.bindConstant
import org.kodein.di.bindSingleton
import org.kodein.di.instance
import uk.co.caprica.vlcj.player.component.AudioPlayerComponent
import java.io.File


val di = DI {
    bindSingleton<DatabaseGenerator> {DatabaseGeneratorImpl(instance())}
    bindSingleton<CountryRepository> {CountryRepositoryImpl(instance())}
    bindSingleton<PlayerService> {PlayerServiceImpl(instance())}
    //bindSingleton<StateKeeper> {()}
    bindSingleton {AudioPlayerComponent()}
    bindSingleton {StationsViewModel(instance(), instance())}
    bindSingleton<Nitrite>{
        Nitrite
            .builder()
            .filePath(".${File.separator}file.db")
            .openOrCreate()
    }
    bindConstant(tag = "defaultHeight") {500}
    bindConstant(tag = "defaultWidth") {900}
}
