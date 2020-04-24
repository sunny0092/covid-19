//package net.ihaha.sunny.newscovid19.ui.start
//
//import androidx.navigation.NavController
//import androidx.navigation.Navigation
//import net.ihaha.sunny.commons.base.BaseFragment
//import net.ihaha.sunny.commons.utils.extensions.mainNavigator
//import net.ihaha.sunny.newscovid19.R
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class StartFragment : BaseFragment<StartViewModel>() {
//
//    override val viewModel: StartViewModel by viewModel()
//
//    override val layoutId: Int get() = R.layout.fragment_start
//
//    override val navController: NavController
//        get() = mainNavigator().hostController
//}