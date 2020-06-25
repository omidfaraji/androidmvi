package com.faraji.challenge.vira.presentation.uploadpage

import androidx.lifecycle.viewModelScope
import com.faraji.challenge.vira.R
import com.faraji.challenge.vira.domain.repository.UploadVideoRepository
import com.faraji.challenge.vira.presentation.base.IntentViewModel
import com.faraji.challenge.vira.presentation.mainpage.GameResultViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.compat.ScopeCompat.viewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File

class UploadViewModel(
    private val uploadVideoRepository: UploadVideoRepository
) : IntentViewModel<UploadIntent, UploadViewState>() {



    override fun processIntents(intents: ReceiveChannel<UploadIntent>) {
        viewModelScope.launch {
            for (intent in intents) {
                when (val action = intentToAction(intent)) {
                    is UploadAction.UploadVideoAction -> uploadVideo(action.videoFie)
                }
            }
        }
    }

    private suspend fun uploadVideo(videoFile: File) {
        actor.send(UploadViewState.InProgress)
        try {
            val result = withContext(Dispatchers.IO) {
                uploadVideoRepository.uploadVideo(videoFile)
            }
            if (result)
                actor.send(UploadViewState.UploadVideoUiModel.Success)
            else
                actor.send(UploadViewState.UploadVideoUiModel.Failed(R.string.error))

        } catch (e: Exception) {
            actor.send(UploadViewState.UploadVideoUiModel.Failed(R.string.error))
        }
    }

    private fun intentToAction(intent: UploadIntent): UploadAction {
        return when (intent) {
            is UploadIntent.UploadVideoIntent -> {
                UploadAction.UploadVideoAction(intent.videoFie)
            }
        }
    }
}