package com.faraji.challenge.vira.presentation

import com.faraji.challenge.vira.presentation.mainpage.GameResultViewModel
import com.faraji.challenge.vira.presentation.uploadpage.UploadViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { GameResultViewModel(get()) }
    viewModel { UploadViewModel(get()) }
}