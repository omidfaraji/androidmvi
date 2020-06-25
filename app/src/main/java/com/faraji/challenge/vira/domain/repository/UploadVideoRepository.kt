package com.faraji.challenge.vira.domain.repository

import java.io.File

interface UploadVideoRepository {
    suspend fun uploadVideo(videoFile: File): Boolean
}