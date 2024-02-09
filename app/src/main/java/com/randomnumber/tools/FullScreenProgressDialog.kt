package com.randomnumber.tools

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.randomnumber.R
import com.randomnumber.core.base.extensions.visible

class FullScreenProgressDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = inflater.inflate(R.layout.full_screen_progress_layout, container, false)
        view.findViewById<FrameLayout>(R.id.progressLayout).visible()
        return view
    }

    override fun show(manager: FragmentManager, tag: String?) {
        if (!isAdded && !isVisible && manager.findFragmentByTag(tag) == null) super.show(
            manager.beginTransaction().remove(this), tag
        )
    }

    override fun getTheme(): Int {
        return R.style.FullScreenDialog
    }
}