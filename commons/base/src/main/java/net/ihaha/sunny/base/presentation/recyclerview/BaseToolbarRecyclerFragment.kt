package net.ihaha.sunny.base.presentation.recyclerview

import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.presentation.holders.BaseRecyclerUI
import net.ihaha.sunny.base.viewModels.IBaseViewModel


abstract class BaseToolbarRecyclerFragment<I : BaseRecyclerUI<*>, out VM: IBaseViewModel> : BaseRecyclerFragment<I, VM>() {

    override val layoutId: Int get() = R.layout.layout_toolbar_recycler

}