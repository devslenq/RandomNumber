package com.randomnumber.core.base.extensions

import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView.OnEditorActionListener


fun EditText.onImeAction(action: () -> Unit) {
    this.setOnEditorActionListener(OnEditorActionListener { v, actionId, event ->
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            action.invoke()
            return@OnEditorActionListener true
        } else return@OnEditorActionListener false
    })
}