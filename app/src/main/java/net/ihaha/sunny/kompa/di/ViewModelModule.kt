package net.ihaha.sunny.kompa.di

import net.ihaha.sunny.kompa.ui.MainViewModel
import net.ihaha.sunny.kompa.ui.guide.GuideViewModel
import net.ihaha.sunny.kompa.ui.help.HelpViewModel
import net.ihaha.sunny.kompa.ui.home.HomeViewModel
import net.ihaha.sunny.kompa.ui.news.NewsViewModel
import net.ihaha.sunny.kompa.ui.prevention.PreventionViewModel
import net.ihaha.sunny.kompa.ui.setting.SettingViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel() }
    viewModel { NewsViewModel() }
    viewModel { PreventionViewModel() }
    viewModel { HelpViewModel() }
    viewModel { GuideViewModel() }
    viewModel { SettingViewModel() }
}