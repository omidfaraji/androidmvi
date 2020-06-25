package com.faraji.challenge.vira.ui.mainpage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.faraji.challenge.vira.R
import com.faraji.challenge.vira.presentation.mainpage.GameResultIntent
import com.faraji.challenge.vira.presentation.mainpage.GameResultViewModel
import com.faraji.challenge.vira.presentation.mainpage.GameResultViewState
import com.faraji.challenge.vira.ui.base.IntentFragment
import com.faraji.challenge.vira.ui.extension.longToast
import kotlinx.android.synthetic.main.fragment_game_result.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class GameResultFragment :
    IntentFragment<GameResultIntent, GameResultViewState, GameResultViewModel>() {

    override val viewModel: GameResultViewModel by viewModel()

    private val adapter by lazy {
        GameResultAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_game_result, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter
        sendIntent(GameResultIntent.InitialIntent)
    }

    override suspend fun handleState(state: GameResultViewState) {
        progressBar.isVisible = state.loading
        when (state) {
            is GameResultViewState.GameResultDataUiModel -> {
                when (state) {
                    is GameResultViewState.GameResultDataUiModel.Success -> {
                        adapter.items = state.gameAdapterItems.toMutableList()
                    }
                    is GameResultViewState.GameResultDataUiModel.Failed -> {
                        longToast(getString(state.message))
                    }
                }
            }
        }
    }


    companion object {
        fun newInstance() = GameResultFragment()
    }
}