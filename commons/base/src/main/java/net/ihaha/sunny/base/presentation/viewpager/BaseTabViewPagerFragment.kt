package net.ihaha.sunny.base.presentation.viewpager

import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.layout_tab_fixed.*
import kotlinx.android.synthetic.main.layout_viewpager.*
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.viewModels.IBaseViewModel

abstract class BaseTabViewPagerFragment<out VM : IBaseViewModel> : BaseViewPagerFragment<VM>(), TabSelectListener {

    //region variable
    abstract val pageTitles: Array<String>

    protected open val tabViewLayout: TabLayout get() = tabLayout as TabLayout
    protected open val selectedPosition: Int get() = viewModel?.getFromSaveState(KEY_POSITION) ?: DEFAULT_POSITION
    protected open fun selectTab(tab: TabLayout.Tab) { selectedTab = tab }
    protected open fun unSelectTab(tab: TabLayout.Tab?) = Unit
    protected open fun setCustomTabView(tab: TabLayout.Tab, position: Int) = Unit

    private var selectedTab: TabLayout.Tab? = null
    private var tabLayoutMediator: TabLayoutMediator? = null
    //endregion
    override val layoutId: Int get() = R.layout.layout_tab_viewpager

    override fun initLayout() {
        super.initLayout()
        tabLayoutMediator = TabLayoutMediator(tabViewLayout, viewPagerLayout) { tab, position ->
            setCustomTabView(tab, position)
        }.apply { attach() }

        viewPagerLayout.setCurrentItem(selectedPosition, false)
        tabViewLayout.addOnTabSelectedListener(this)
    }

    override fun onTabSelected(tab: TabLayout.Tab) {
        viewModel?.addToSaveState(KEY_POSITION, tab.position)
        unSelectTab(selectedTab)
        selectTab(tab)
        selectedTab = tab
    }

    override fun onDestroyView() {
        selectedTab = null
        tabLayoutMediator?.detach()
        tabLayoutMediator = null
        super.onDestroyView()
    }

    //region method

    //endregion

    companion object {
        private const val KEY_POSITION = "SELECTED_POSITION"
        private const val DEFAULT_POSITION = 0
    }
}
