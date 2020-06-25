package com.faraji.challenge.vira.data

import com.faraji.challenge.vira.data.local.OfflineGameProvider
import com.faraji.challenge.vira.data.local.OfflineGameProviderImpl
import com.faraji.challenge.vira.data.repository.GameRepositoryImpl
import com.faraji.challenge.vira.data.repository.UploadVideoRepositoryImpl
import com.faraji.challenge.vira.domain.repository.GameRepository
import com.faraji.challenge.vira.domain.repository.UploadVideoRepository
import org.koin.dsl.bind
import org.koin.dsl.module


val dataProviderModule = module {
    factory { OfflineGameProviderImpl() } bind OfflineGameProvider::class
}
val repositoryModule = module {
    factory { GameRepositoryImpl(get()) } bind GameRepository::class
    factory { UploadVideoRepositoryImpl(get(), get()) } bind UploadVideoRepository::class
}