package net.ihaha.sunny.base.presentation.recyclerview.paged

import com.mikepenz.fastadapter.paged.ExperimentalPagedSupport
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.presentation.holders.BaseRecyclerUI
import net.ihaha.sunny.base.viewModels.IBaseViewModel

@ExperimentalPagedSupport
abstract class BaseToolbarRefreshPagedRecyclerFragment<Item : BaseRecyclerUI<*>, out VM: IBaseViewModel> : BasePagedRefreshRecyclerFragment<Item, VM>() {

    override val layoutId: Int get() = R.layout.layout_toolbar_refresh_recycler

}