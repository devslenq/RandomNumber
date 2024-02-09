package com.randomnumber.core.base.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<V : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: V
        private set

    protected abstract fun initBinding(inflater: LayoutInflater): V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initBinding(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.setupLayout()
    }

    abstract fun V.setupLayout()

}