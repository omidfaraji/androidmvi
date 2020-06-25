package com.faraji.challenge.vira.presentation.mainpage

import com.faraji.languagetopically.presentation.base.BaseIntent

sealed class GameResultIntent : BaseIntent {
    object InitialIntent : GameResultIntent()
}