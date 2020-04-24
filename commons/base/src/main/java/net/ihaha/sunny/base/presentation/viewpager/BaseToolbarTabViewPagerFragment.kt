package net.ihaha.sunny.base.presentation.viewpager


import androidx.databinding.ViewDataBinding
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.viewModels.IBaseViewModel

abstract class BaseToolbarTabViewPagerFragment<out VM : IBaseViewModel> : BaseTabViewPagerFragment<VM>(), TabSelectListener {

    override val layoutId: Int get() = R.layout.layout_toolbar_tab_viewpager


}
