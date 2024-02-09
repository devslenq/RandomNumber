package com.randomnumber.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doOnTextChanged
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.randomnumber.R
import com.randomnumber.core.base.extensions.onImeAction
import com.randomnumber.core.base.extensions.onSingleClick
import com.randomnumber.core.base.ui.BaseFragment
import com.randomnumber.core.base.ui.VerticalSpaceItemDecorator
import com.randomnumber.core.data.network.models.ItemNumberModel
import com.randomnumber.databinding.FragmentNumberBinding
import com.randomnumber.tools.FullscreenProgressDialogDelegate
import com.randomnumber.tools.FullscreenProgressDialogDelegateImpl
import com.randomnumber.tools.NumberInputFilter
import com.randomnumber.ui.NumberInfoFragment.Companion.NUMBER_INFO_ITEM
import com.randomnumber.ui.viewmodel.NumberViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class NumberFragment : BaseFragment<FragmentNumberBinding>(),
    FullscreenProgressDialogDelegate by FullscreenProgressDialogDelegateImpl() {

    private val viewModel: NumberViewModel by viewModel()
    private val historyListAdapter by lazy {
        NumberAdapter(onClickItem = ::onClickNumber)
    }

    override fun initBinding(
        layoutInflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentNumberBinding.inflate(layoutInflater, container, false)


    private fun handleAboutNumber(itemNumberModel: ItemNumberModel?) {
        dismissProgress()
        binding.apply {
            aboutNumber.text = itemNumberModel?.text
            itemNumberModel?.let {
                historyListAdapter.putItem(it)
            }
            val bottomSheetBehavior: BottomSheetBehavior<*> =
                BottomSheetBehavior.from(binding.bottomSheetBehavior)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }
    }

    override fun FragmentNumberBinding.setupLayout() {
        initializeObservers()
        getInfo.onSingleClick {
            val field = numberField.text
            if (field.isNullOrEmpty()) {
                numberFieldLayout.isErrorEnabled = true
                return@onSingleClick
            }
            showProgress(childFragmentManager)
            viewModel.getInfoAboutNumber(numberField.text.toString().toIntOrNull() ?: 0)
        }
        getRandomInfo.onSingleClick {
            showProgress(childFragmentManager)
            viewModel.getRandomInfoAboutNumber()
        }
        numberField.apply {
            filters = arrayOf(NumberInputFilter())
            onImeAction {
                hideKeyboard()
                getInfo.performClick()
            }
            doOnTextChanged { text, start, before, count ->
                numberFieldLayout.isErrorEnabled = false
            }
        }
        setupAdapter()
        viewModel.getLocalData()
    }

    private fun initializeObservers() {
        viewModel.aboutNumberLiveData.observe(viewLifecycleOwner, ::handleAboutNumber)
        viewModel.savedItemsNumberLiveData.observe(viewLifecycleOwner, ::handleLocalData)
        setupAdapter()
    }

    private fun handleLocalData(itemNumberModels: List<ItemNumberModel>?) {
        setupAdapter()
        historyListAdapter.putItems(itemNumberModels ?: emptyList())
    }

    private fun setupAdapter() = binding.run {
        if (binding.numberHistoryList.adapter !is NumberAdapter) {
            numberHistoryList.adapter = historyListAdapter
            if (numberHistoryList.itemDecorationCount == 0)
                numberHistoryList.addItemDecoration(
                    VerticalSpaceItemDecorator(
                        resources.getDimensionPixelSize(
                            R.dimen.dp_8
                        )
                    )
                )
        }
    }

    private fun onClickNumber(item: ItemNumberModel) {
        findNavController().navigate(R.id.showNumberInfo, args = bundleOf(NUMBER_INFO_ITEM to item))
    }

}