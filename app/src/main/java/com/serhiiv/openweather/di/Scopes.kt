package com.serhiiv.openweather.di

import javax.inject.Scope

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
annotation class PerApplication

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
annotation class PerActivity

@Scope
@MustBeDocumented
@Retention(AnnotationRetention.SOURCE)
annotation class PerFragment
