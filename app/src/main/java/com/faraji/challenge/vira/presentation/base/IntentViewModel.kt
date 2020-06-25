package com.faraji.challenge.vira.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.faraji.languagetopically.presentation.base.BaseIntent
import com.faraji.languagetopically.presentation.base.BaseViewState
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import org.koin.core.KoinComponent
import kotlin.random.Random

abstract class IntentViewModel<I : BaseIntent, S : BaseViewState>
    : ViewModel(), BaseViewModel<I, S> {

    protected var actor: Channel<S> = Channel()

    override fun states(): LiveData<S> =
        actor.receiveAsFlow().asLiveData()

    override fun onCleared() {
        actor.close()
    }

}