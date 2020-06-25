package com.faraji.challenge.vira.presentation.mainpage

import com.faraji.languagetopically.presentation.base.BaseAction

sealed class GameResultAction : BaseAction {
    object InitialAction : GameResultAction()
}