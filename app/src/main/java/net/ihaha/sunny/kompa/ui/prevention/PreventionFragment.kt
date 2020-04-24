package net.ihaha.sunny.kompa.ui.prevention

import net.ihaha.sunny.base.presentation.fragment.BaseBindingFragment
import net.ihaha.sunny.kompa.R
import net.ihaha.sunny.kompa.databinding.FragmentPreventionBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class PreventionFragment : BaseBindingFragment<FragmentPreventionBinding, PreventionViewModel>() {

    //region override

    override val viewModel: PreventionViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_prevention

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