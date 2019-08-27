package com.serhiiv.openweather.di

import com.serhiiv.openweather.core.domain.interactor.GetForecastByCityName
import com.serhiiv.openweather.core.domain.pipeline.ChangeUnitsEventPipeline
import com.serhiiv.openweather.domain.interactor.GetForecastByCityNameUseCaseImpl
import com.serhiiv.openweather.domain.pipeline.ChangeUnitsEventPipelineImpl
import dagger.Binds
import dagger.Module

@Module(
    includes = [
        DomainModule.InteractorModule::class,
        DomainModule.PipelinesModule::class
    ]
)
interface DomainModule {

    @Module
    interface InteractorModule {
        @Binds
        fun bindGetForecastByCityNameUseCase(impl: GetForecastByCityNameUseCaseImpl): GetForecastByCityName
    }

    @Module
    interface PipelinesModule {
        @Binds
        @PerApplication
        fun bindChangeUnitEventPipeline(impl: ChangeUnitsEventPipelineImpl): ChangeUnitsEventPipeline
    }
}
