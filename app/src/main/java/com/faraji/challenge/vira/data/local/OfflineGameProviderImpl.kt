package com.faraji.challenge.vira.data.local

import com.faraji.challenge.vira.R
import com.faraji.challenge.vira.domain.model.Game
import com.faraji.challenge.vira.domain.model.Team

class OfflineGameProviderImpl : OfflineGameProvider {
    override suspend fun getAllOfflineGames(): List<Game> {
        return listOf(
            Game(
                firstTeam = Team("منچستر یونایتد", R.drawable.team_logo),
                secondTeam = Team("منچستر سیتی", R.drawable.team_logo),
                isLive = true,
                isCanceled = false,
                isFinished = false,
                spendTime = 25,
                time = null,
                result = "2:0"
            ),
            Game(
                firstTeam = Team("چلسی", R.drawable.team_logo),
                secondTeam = Team("بورنلی", R.drawable.team_logo),
                isLive = false,
                isCanceled = true,
                isFinished = false,
                spendTime = null,
                time = null,
                result = null
            ),
            Game(
                firstTeam = Team("استون ویلا", R.drawable.team_logo),
                secondTeam = Team("برایتون", R.drawable.team_logo),
                isLive = false,
                isCanceled = false,
                isFinished = false,
                spendTime = null,
                time = "22:30",
                result = null
            ),
            Game(
                firstTeam = Team("برایتون", R.drawable.team_logo),
                secondTeam = Team("لستر سیتی", R.drawable.team_logo),
                isLive = false,
                isCanceled = false,
                isFinished = false,
                spendTime = null,
                time = "22:30",
                result = null
            ),
            Game(
                firstTeam = Team("منچستر یونایتد", R.drawable.team_logo),
                secondTeam = Team("منچستر سیتی", R.drawable.team_logo),
                isLive = true,
                isCanceled = false,
                isFinished = false,
                spendTime = 25,
                time = null,
                result = "2:0"
            ),
            Game(
                firstTeam = Team("چلسی", R.drawable.team_logo),
                secondTeam = Team("بورنلی", R.drawable.team_logo),
                isLive = false,
                isCanceled = true,
                isFinished = false,
                spendTime = null,
                time = null,
                result = null
            ),
            Game(
                firstTeam = Team("استون ویلا", R.drawable.team_logo),
                secondTeam = Team("برایتون", R.drawable.team_logo),
                isLive = false,
                isCanceled = false,
                isFinished = false,
                spendTime = null,
                time = "22:30",
                result = null
            ),
            Game(
                firstTeam = Team("برایتون", R.drawable.team_logo),
                secondTeam = Team("لستر سیتی", R.drawable.team_logo),
                isLive = false,
                isCanceled = false,
                isFinished = false,
                spendTime = null,
                time = "22:30",
                result = null
            )
        )
    }

}