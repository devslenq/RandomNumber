package com.randomnumber

import android.view.LayoutInflater
import com.randomnumber.core.base.ui.BaseActivity
import com.randomnumber.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun initBinding(inflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(inflater)
    }

    override fun ActivityMainBinding.setupLayout() {}
}