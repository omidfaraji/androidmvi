package com.faraji.challenge.vira.domain.repository

import com.faraji.challenge.vira.presentation.model.GameAdapterItem

interface GameRepository {
    suspend fun getAllGames(): List<GameAdapterItem>
}