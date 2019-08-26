package com.serhiiv.openweather.di

import com.serhiiv.openweather.core.domain.pipeline.ChangeUnitsEventPipeline
import com.serhiiv.openweather.domain.pipeline.ChangeUnitsEventPipelineImpl
import dagger.Binds
import dagger.Module

@Module(includes = [DomainModule.PipelinesModule::class])
interface DomainModule {

    @Module
    interface PipelinesModule {
        @Binds
        @PerApplication
        fun bindChangeUnitEventPipeline(impl: ChangeUnitsEventPipelineImpl): ChangeUnitsEventPipeline
    }
}
