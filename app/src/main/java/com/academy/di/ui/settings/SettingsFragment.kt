package com.academy.di.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.academy.di.App
import com.academy.di.R
import javax.inject.Inject

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    @Inject
    internal lateinit var settingsViewModelFactory: SettingsViewModelFactory
    private val viewModel: SettingsViewModel by viewModels { settingsViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        App.component.inject(this)
        viewModel.kuku()
    }
}