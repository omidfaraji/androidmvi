package com.faraji.challenge.vira.presentation.uploadpage

import androidx.annotation.StringRes
import com.faraji.languagetopically.presentation.base.BaseViewState

sealed class UploadViewState(val loading: Boolean) : BaseViewState {
    object InProgress : UploadViewState(true)

    sealed class UploadVideoUiModel(inProgress: Boolean = false) :
        UploadViewState(inProgress) {
        object Success : UploadVideoUiModel(false)
        class Failed(@StringRes val message: Int) : UploadVideoUiModel(false)
    }
}