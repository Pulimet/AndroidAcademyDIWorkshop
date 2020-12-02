package com.academy.di

import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.academy.di.ui.navigation.NavigationViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main){
    private val navViewModel: NavigationViewModel by viewModels()
    private lateinit var navController: NavController

    override fun onStart() {
        super.onStart()
        navController = findNavController(R.id.nav_host_fragment)
        navViewModel.getNavEvent().observe(this) {
            navController.navigate(it.first, it.second)
        }
    }
}