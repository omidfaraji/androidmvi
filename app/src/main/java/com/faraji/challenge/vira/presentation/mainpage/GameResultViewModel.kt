package com.faraji.challenge.vira.presentation.mainpage

import androidx.lifecycle.viewModelScope
import com.faraji.challenge.vira.R
import com.faraji.challenge.vira.domain.repository.GameRepository
import com.faraji.challenge.vira.presentation.base.IntentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

class GameResultViewModel(
    private val gameRepository: GameRepository
) : IntentViewModel<GameResultIntent, GameResultViewState>() {

    override fun processIntents(intents: ReceiveChannel<GameResultIntent>) {
        viewModelScope.launch {
            for (intent in intents) {
                when (val action = intentToAction(intent)) {
                    is GameResultAction.InitialAction -> loadData()
                }
            }
        }
    }

    private suspend fun loadData() {
        actor.send(GameResultViewState.InProgress)
        try {
            val result = withContext(Dispatchers.IO) {
                gameRepository.getAllGames()
            }
            actor.send(GameResultViewState.GameResultDataUiModel.Success(result))
        } catch (e: Exception) {
            actor.send(GameResultViewState.GameResultDataUiModel.Failed(R.string.error))
            Timber.i("after Failed")
        }
    }

    private fun intentToAction(intent: GameResultIntent): GameResultAction {
        return when (intent) {
            is GameResultIntent.InitialIntent -> {
                GameResultAction.InitialAction
            }
        }
    }
}