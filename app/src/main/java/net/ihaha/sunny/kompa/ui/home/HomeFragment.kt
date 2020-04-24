package net.ihaha.sunny.kompa.ui.home

import android.view.LayoutInflater
import com.google.android.material.tabs.TabLayout
import com.mikepenz.fastadapter.paged.ExperimentalPagedSupport
import kotlinx.android.synthetic.main.layout_item_tab.view.*
import net.ihaha.sunny.base.presentation.fragment.BaseFragment
import net.ihaha.sunny.base.presentation.viewpager.BaseTabViewPagerFragment
import net.ihaha.sunny.base.presentation.viewpager.BaseToolbarTabViewPagerFragment
import net.ihaha.sunny.base.utils.extensions.color
import net.ihaha.sunny.base.utils.extensions.drawable
import net.ihaha.sunny.base.utils.extensions.unsafeLazy
import net.ihaha.sunny.kompa.R
import org.koin.androidx.viewmodel.ext.android.viewModel


@ExperimentalPagedSupport
class HomeFragment : BaseTabViewPagerFragment<HomeViewModel>() {

    //region variable

    //endregion

    //region override

    override val viewModel: HomeViewModel by viewModel()

    override val pageTitles: Array<String> by unsafeLazy {
        resources.getStringArray(R.array.title_map_array)
    }

    override val pages: List<BaseFragment<*>> by unsafeLazy {
        listOf(
            HomeLocalMapFragment.newInstance(),
            HomeWorldMapFragment.newInstance()
        )
    }

    private val tabBackground by unsafeLazy { drawable(R.drawable.bg_tab_selected) }
    private val selectedTabColor by unsafeLazy { color(R.color.white) }
    private val unselectedTabColor by unsafeLazy { color(R.color.blue_300) }

    override fun setCustomTabView(tab: TabLayout.Tab, position: Int) {
        val tabView = LayoutInflater.from(context).inflate(R.layout.layout_item_tab, null)
        tabView.tabTextView.text = pageTitles[position]
        tab.customView = tabView
        if (selectedPosition == position) selectTab(tab)
    }

    override fun selectTab(tab: TabLayout.Tab) {
        super.selectTab(tab)
        tab.customView?.tabTextView?.apply {
            setTextColor(selectedTabColor)
            background = tabBackground
        }
    }

    override fun unSelectTab(tab: TabLayout.Tab?) {
        tab?.customView?.tabTextView?.apply {
            setTextColor(unselectedTabColor)
            background = null
        }
    }

    override fun initBinding() {

    }

    //endregion






}