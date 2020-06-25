package com.faraji.challenge.vira.ui.extension

import android.content.Context
import android.widget.Toast

fun Context.longToast(msg: String): Toast {
    return Toast.makeText(this, msg, Toast.LENGTH_LONG).also { it.show() }
}
