package com.academy.di.ui.base

import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BindFragment<T : ViewBinding>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    private var _binding: T? = null
    protected val binding get() = _binding!!

    protected fun bindIt(bind: T) {
        _binding = bind
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}