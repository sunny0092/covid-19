package net.ihaha.sunny.base.presentation.recyclerview.paged
import android.os.Bundle
import android.view.View
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mikepenz.fastadapter.paged.ExperimentalPagedSupport
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.presentation.holders.BaseRecyclerUI
import net.ihaha.sunny.base.viewModels.IBaseViewModel

@ExperimentalPagedSupport
abstract class BasePagedRefreshRecyclerFragment<Item : BaseRecyclerUI<*>, out VM: IBaseViewModel> : BasePagedRecyclerFragment<Item, VM>(), SwipeRefreshLayout.OnRefreshListener {

    override val layoutId: Int get() = R.layout.layout_refresh_recycler

    @Suppress("UNCHECKED_CAST")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}