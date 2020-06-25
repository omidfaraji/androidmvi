package com.faraji.challenge.vira.presentation.uploadpage

import com.faraji.languagetopically.presentation.base.BaseIntent
import java.io.File

sealed class UploadIntent : BaseIntent {
    class UploadVideoIntent(val videoFie: File) : UploadIntent()
}