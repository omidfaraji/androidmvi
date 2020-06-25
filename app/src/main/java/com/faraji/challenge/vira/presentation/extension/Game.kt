package com.faraji.challenge.vira.presentation.extension

import com.faraji.challenge.vira.domain.model.Game
import com.faraji.challenge.vira.presentation.model.*

fun Game.toGameAdapterItem(): GameAdapterItem {
    val type = when {
        isLive -> TYPE_LIVE
        isCanceled -> TYPE_CANCELED
        isFinished -> TYPE_FINISHED
        else -> TYPE_NORMAL
    }
   return GameAdapterItem(this, type)
}