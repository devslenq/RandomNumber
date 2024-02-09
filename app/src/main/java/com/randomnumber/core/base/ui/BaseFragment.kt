package com.randomnumber.core.base.ui

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<V : ViewBinding> : Fragment() {
    protected lateinit var binding: V
        private set

    val parentActivity: BaseActivity<*> by lazy { requireActivity() as BaseActivity<*> }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = initBinding(inflater, container)
        binding.setupLayout()
        return binding.root
    }

    protected abstract fun initBinding(layoutInflater: LayoutInflater, container: ViewGroup?): V

    abstract fun V.setupLayout()

    fun hideKeyboard() {
        context?.let {
            val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    fun showKeyboard(forView: View?) {
        context?.let {
            forView?.requestFocus()
            val imm = it.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(forView, InputMethodManager.SHOW_IMPLICIT)
        }
    }
}