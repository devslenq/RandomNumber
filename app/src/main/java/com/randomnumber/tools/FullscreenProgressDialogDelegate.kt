package com.randomnumber.tools

import androidx.fragment.app.FragmentManager

interface FullscreenProgressDialogDelegate {
    fun showProgress(fm: FragmentManager)
    fun dismissProgress()
    fun isProgressVisible(): Boolean
}

class FullscreenProgressDialogDelegateImpl : FullscreenProgressDialogDelegate {
    private var progressDialog: FullScreenProgressDialog? = null

    override fun showProgress(fm: FragmentManager) {
        if (progressDialog == null) {
            progressDialog = FullScreenProgressDialog()
        }

        progressDialog?.let {
            if (!it.isAdded && !it.isVisible && fm.findFragmentByTag(TAG) == null) {
                it.show(fm.beginTransaction().remove(it), TAG)
            }
        }
    }

    override fun dismissProgress() {
        progressDialog?.dismiss()
        progressDialog = null
    }

    override fun isProgressVisible(): Boolean {
        return progressDialog != null && progressDialog?.isVisible == true
    }

    private companion object {
        private const val TAG = "FullscreenProgressDialogDelegate"
    }
}