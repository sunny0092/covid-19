package net.ihaha.sunny.kompa.ui.guide

import android.os.Bundle
import net.ihaha.sunny.base.presentation.fragment.BaseBindingFragment
import net.ihaha.sunny.kompa.R
import net.ihaha.sunny.kompa.databinding.FragmentGuideBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class GuideFragment : BaseBindingFragment<FragmentGuideBinding, GuideViewModel>() {

    //region override

    override val viewModel: GuideViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_guide

    //endregion

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object{
        fun newInstance() = GuideFragment()
    }

    override fun initBinding() {
        TODO("Not yet implemented")
    }

}