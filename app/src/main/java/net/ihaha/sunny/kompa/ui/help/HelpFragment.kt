package net.ihaha.sunny.kompa.ui.help

import net.ihaha.sunny.base.presentation.fragment.BaseBindingFragment
import net.ihaha.sunny.kompa.R
import net.ihaha.sunny.kompa.databinding.FragmentHelpBinding
import net.ihaha.sunny.kompa.ui.help.HelpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class HelpFragment : BaseBindingFragment<FragmentHelpBinding, HelpViewModel>() {

    //region override

    override val viewModel: HelpViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_help

    override fun initBinding() {
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            executePendingBindings()
        }
    }



    //endregion

    //region method

    //endregion

}