package  com.faraji.challenge.vira.presentation.base

import androidx.lifecycle.LiveData
import com.faraji.languagetopically.presentation.base.BaseIntent
import com.faraji.languagetopically.presentation.base.BaseViewState
import kotlinx.coroutines.channels.ReceiveChannel

interface BaseViewModel<I : BaseIntent, S : BaseViewState> {

    fun processIntents(intents: ReceiveChannel<I>)

    fun states(): LiveData<S>

}