package com.academy.di.di.components

import com.academy.di.di.modules.SettingsModule
import com.academy.di.di.scopes.SettingsScope
import com.academy.di.ui.settings.SettingsFragment
import dagger.Subcomponent

@Subcomponent(modules = [SettingsModule::class])
@SettingsScope
interface SettingsComponent {
    fun inject(settingsFragment: SettingsFragment)
}