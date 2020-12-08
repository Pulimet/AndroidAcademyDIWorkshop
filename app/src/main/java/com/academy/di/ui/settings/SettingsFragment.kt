package com.academy.di.ui.settings

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.academy.di.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {
    private val viewModel: SettingsViewModel by viewModels()
}