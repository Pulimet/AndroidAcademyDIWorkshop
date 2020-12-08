package com.academy.di.di.modules

import com.academy.di.di.scopes.SettingsScope
import com.academy.di.repo.SettingsRepo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SettingsModule {
    @Provides
    @SettingsScope
    fun getSettingsRepo() = SettingsRepo()
}