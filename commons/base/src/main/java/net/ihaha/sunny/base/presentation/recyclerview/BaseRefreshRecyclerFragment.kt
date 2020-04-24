package net.ihaha.sunny.base.presentation.recyclerview

import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.layout_refresh_recycler.*
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.presentation.holders.BaseRecyclerUI
import net.ihaha.sunny.base.viewModels.IBaseViewModel

abstract class BaseRefreshRecyclerFragment<I : BaseRecyclerUI<*>, out VM : IBaseViewModel> :
    BaseRecyclerFragment<I, VM>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutId: Int
        get() = R.layout.layout_refresh_recycler

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (refreshLayout as SwipeRefreshLayout).setOnRefreshListener(this)
    }

    override fun onRefresh() {
        (refreshLayout as SwipeRefreshLayout).isRefreshing = false
    }

    override fun onDestroyView() {
        (refreshLayout as SwipeRefreshLayout).setOnRefreshListener(null)
        super.onDestroyView()
    }
}
