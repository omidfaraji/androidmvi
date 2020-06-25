package com.faraji.challenge.vira.data.local

import com.faraji.challenge.vira.domain.model.Game

interface OfflineGameProvider {
    suspend fun getAllOfflineGames(): List<Game>
}