package com.academy.di.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.academy.di.R
import com.academy.di.di.Injector
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment(R.layout.fragment_settings), View.OnClickListener {
    @Inject
    internal lateinit var settingsViewModelFactory: SettingsViewModelFactory
    private val viewModel: SettingsViewModel by viewModels { settingsViewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Injector.getSettingsComponent().inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeViewModel()
        setOnClickListenerForViews(btnMinusVotes, btnPlusVotes, btnMinusRating, btnPlusRating)
    }

    private fun observeViewModel() {
        viewModel.apply {
            minVotesLiveData.observe(viewLifecycleOwner) { tvMinVotesValue.text = it.toString() }
            minRatingLiveData.observe(viewLifecycleOwner) { tvMinRatingValue.text = it.toString() }
        }
    }

    private fun setOnClickListenerForViews(vararg views: View) {
        views.forEach { it.setOnClickListener(this) }
    }

    // View.OnClickListener
    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnMinusVotes -> viewModel.onBtnMinusVotesNumClick()
            R.id.btnPlusVotes -> viewModel.onBtnPlusVotesNumClick()
            R.id.btnMinusRating -> viewModel.onBtnMinusRatingClick()
            R.id.btnPlusRating -> viewModel.onBtnPlusRatingClick()
        }
    }
}