package com.academy.di.di.modules

import com.academy.di.repo.SettingsRepo
import dagger.Module
import dagger.Provides

@Module
class SettingsModule {
    @Provides
    fun getSettingsRepo() = SettingsRepo()
}