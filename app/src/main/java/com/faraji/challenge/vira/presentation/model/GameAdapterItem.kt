package com.faraji.challenge.vira.presentation.model

import com.faraji.challenge.vira.domain.model.Game

const val TYPE_LIVE = 0
const val TYPE_CANCELED = 1
const val TYPE_FINISHED = 2
const val TYPE_NORMAL = 3

data class GameAdapterItem(val game: Game, val type: Int)

