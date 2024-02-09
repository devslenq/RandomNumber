package com.randomnumber.tools

import android.text.InputFilter
import android.text.Spanned

class NumberInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        val builder = StringBuilder(dest.toString())
        builder.delete(dstart, dend)

        builder.insert(dstart, source)

        if (builder.length > 5 || !builder.matchesRegex("\\d*")) {
            return ""
        }

        return null
    }

    private fun CharSequence.matchesRegex(regex: String): Boolean {
        return this.matches(Regex(regex))
    }
}