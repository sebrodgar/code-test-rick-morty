package com.srg.codetestrickmorty.common.errors

import androidx.annotation.StringRes

class DialogErrorViewEntity(
    @StringRes val dialogMessage: Int = -1,
    @StringRes val positiveButton: Int = android.R.string.ok,
    @StringRes val dialogTitle: Int = -1,
    @StringRes val negativeButton: Int = android.R.string.cancel,
    val varStrings: Array<String?> = arrayOf()
)