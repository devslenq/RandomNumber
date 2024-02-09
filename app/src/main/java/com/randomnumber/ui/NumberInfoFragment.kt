package com.randomnumber.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.randomnumber.core.base.ui.BaseFragment
import com.randomnumber.core.data.network.models.ItemNumberModel
import com.randomnumber.databinding.FragmentNumberInfoBinding

class NumberInfoFragment : BaseFragment<FragmentNumberInfoBinding>() {
    override fun initBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNumberInfoBinding.inflate(layoutInflater, container, false)

    override fun FragmentNumberInfoBinding.setupLayout() {

        val numberItem = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(NUMBER_INFO_ITEM, ItemNumberModel::class.java)
        } else {
            arguments?.getParcelable(NUMBER_INFO_ITEM)
        }
        if (numberItem != null) {
            number.text = numberItem.number.toString()
            numberDescription.text = numberItem.text.toString()
        } else findNavController().popBackStack()

    }

    companion object {
        const val NUMBER_INFO_ITEM = "NUMBER_INFO_ITEM"
    }
}