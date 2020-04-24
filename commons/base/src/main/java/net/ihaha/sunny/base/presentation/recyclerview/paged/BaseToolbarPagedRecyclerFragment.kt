package net.ihaha.sunny.base.presentation.recyclerview.paged

import com.mikepenz.fastadapter.paged.ExperimentalPagedSupport
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.presentation.holders.BaseRecyclerUI
import net.ihaha.sunny.base.viewModels.IBaseViewModel

@ExperimentalPagedSupport
abstract class BaseToolbarPagedRecyclerFragment<Item : BaseRecyclerUI<*>, out VM: IBaseViewModel> : BasePagedRecyclerFragment<Item, VM>() {

    override val layoutId: Int get() = R.layout.layout_toolbar_recycler
}