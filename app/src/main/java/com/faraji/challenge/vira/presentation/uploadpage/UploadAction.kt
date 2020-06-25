package com.faraji.challenge.vira.presentation.uploadpage

import com.faraji.languagetopically.presentation.base.BaseAction
import java.io.File

sealed class UploadAction : BaseAction {
    class UploadVideoAction(val videoFie: File) : UploadAction()
}