package com.faraji.challenge.vira.presentation.extension

import kotlinx.coroutines.channels.Channel
import timber.log.Timber


suspend fun <T> Channel<T>.safeSend(data: T) {
    if (isClosedForSend.not())
        send(data)
    else
        Timber.e("Chanel is Closed")
}