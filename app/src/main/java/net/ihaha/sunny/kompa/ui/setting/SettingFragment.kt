package net.ihaha.sunny.kompa.ui.setting

import android.os.Bundle
import net.ihaha.sunny.base.presentation.fragment.BaseBindingFragment
import net.ihaha.sunny.kompa.R
import net.ihaha.sunny.kompa.databinding.FragmentSettingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingFragment : BaseBindingFragment<FragmentSettingBinding, SettingViewModel>() {

    //region override

    override val viewModel: SettingViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_setting

    //endregion

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object{
        fun newInstance() = SettingFragment()
    }

    override fun initBinding() {
        TODO("Not yet implemented")
    }

}