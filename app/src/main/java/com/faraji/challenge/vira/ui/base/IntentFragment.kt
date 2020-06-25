package com.faraji.challenge.vira.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.faraji.challenge.vira.presentation.base.IntentViewModel
import com.faraji.challenge.vira.ui.extension.suspendObserve
import com.faraji.languagetopically.presentation.base.BaseIntent
import com.faraji.languagetopically.presentation.base.BaseViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

abstract class IntentFragment<I : BaseIntent, S : BaseViewState, VM : IntentViewModel<I, S>> :
    Fragment() {

    abstract val viewModel: VM

    protected val intentChannel = Channel<I>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.processIntents(intentChannel)
        subscribeForStateUpdates()
    }

    private fun subscribeForStateUpdates() {
        viewModel.states().suspendObserve(
            viewLifecycleOwner,
            Dispatchers.Main,
            onChange = { state ->
                handleState(state)
            }
        )
    }

    abstract suspend fun handleState(state: S)


    fun sendIntent(intent: I) {
        lifecycleScope.launch {
            intentChannel.send(intent)
        }
    }


    override fun onDestroy() {
        intentChannel.close()
        super.onDestroy()
    }

}