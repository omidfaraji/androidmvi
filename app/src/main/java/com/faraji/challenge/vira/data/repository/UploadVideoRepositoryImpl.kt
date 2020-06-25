package com.faraji.challenge.vira.data.repository

import android.content.Context
import com.faraji.challenge.vira.app.ApiService
import com.faraji.challenge.vira.domain.repository.UploadVideoRepository
import kotlinx.coroutines.delay
import java.io.File

class UploadVideoRepositoryImpl(val context: Context, private val apiService: ApiService) :
    UploadVideoRepository {
    override suspend fun uploadVideo(videoFile: File): Boolean {
//        val type = context.contentResolver.getType(Uri.fromFile(videoFile))?.toMediaType()
//        val fileReqBody = videoFile.asRequestBody(type)
//        val part = MultipartBody.Part.createFormData("videoFile", videoFile.name, fileReqBody)
//        return apiService.uploadAvatar(part).await()
        delay(4000)
        return true
    }

}