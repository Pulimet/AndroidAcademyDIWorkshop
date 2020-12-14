package com.academy.di.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.academy.di.R
import com.academy.di.databinding.FragmentSettingsBinding
import com.academy.di.ui.base.BindFragment

class SettingsFragment : BindFragment<FragmentSettingsBinding>(R.layout.fragment_settings),
    View.OnClickListener {
    private val viewModel: SettingsViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindIt(FragmentSettingsBinding.bind(view))
        observeViewModel()
        binding.apply {
            setOnClickListenerForViews(btnMinusVotes, btnPlusVotes, btnMinusRating, btnPlusRating)
        }
    }

    private fun observeViewModel() {
        viewModel.apply {
            minVotesLiveData.observe(viewLifecycleOwner) {
                binding.tvMinVotesValue.text = it.toString()
            }
            minRatingLiveData.observe(viewLifecycleOwner) {
                binding.tvMinRatingValue.text = it.toString()
            }
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