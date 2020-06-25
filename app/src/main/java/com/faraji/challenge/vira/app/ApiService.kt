package com.faraji.challenge.vira.app

import kotlinx.coroutines.Deferred
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.PUT
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @PUT("/uploadVideo")
    fun uploadAvatar(
        @Part file: MultipartBody.Part
    ): Deferred<Boolean>

}
