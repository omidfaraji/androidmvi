package com.faraji.challenge.vira.data.repository

import com.faraji.challenge.vira.data.local.OfflineGameProvider
import com.faraji.challenge.vira.domain.repository.GameRepository
import com.faraji.challenge.vira.presentation.extension.toGameAdapterItem
import com.faraji.challenge.vira.presentation.model.GameAdapterItem

class GameRepositoryImpl(private val offlineGameProvider: OfflineGameProvider) : GameRepository {
    override suspend fun getAllGames(): List<GameAdapterItem> {
        return offlineGameProvider.getAllOfflineGames().map { it.toGameAdapterItem() }
    }
}