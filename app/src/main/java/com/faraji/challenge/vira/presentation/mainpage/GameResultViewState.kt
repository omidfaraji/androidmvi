package com.faraji.challenge.vira.presentation.mainpage

import androidx.annotation.StringRes
import com.faraji.challenge.vira.presentation.model.GameAdapterItem
import com.faraji.languagetopically.presentation.base.BaseViewState

sealed class GameResultViewState(val loading: Boolean) : BaseViewState {
    object InProgress : GameResultViewState(true)

    sealed class GameResultDataUiModel(inProgress: Boolean = false) :
        GameResultViewState(inProgress) {
        class Success(val gameAdapterItems: List<GameAdapterItem>) : GameResultDataUiModel(false)
        class Failed(@StringRes val message: Int) : GameResultDataUiModel(false)
    }
}