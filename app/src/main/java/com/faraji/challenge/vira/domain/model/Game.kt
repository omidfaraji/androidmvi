package com.faraji.challenge.vira.domain.model

data class Game(
    val firstTeam: Team,
    val secondTeam: Team,
    val isLive: Boolean,
    val isCanceled: Boolean,
    val isFinished: Boolean,
    val spendTime: Int?,
    val time: String?,
    val result: String?
)