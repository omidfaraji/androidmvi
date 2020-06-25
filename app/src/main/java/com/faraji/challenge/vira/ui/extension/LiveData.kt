package com.faraji.challenge.vira.ui.extension

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

fun <T> LiveData<T>.suspendObserve(
        owner: LifecycleOwner,
        context: CoroutineContext = EmptyCoroutineContext,
        onChange: suspend (T) -> Unit
) {
    observe(owner, Observer { state ->
        owner.lifecycleScope.launch(context) {
            onChange(state)
        }
    })
}