package net.ihaha.sunny.base.presentation.viewpager

import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.layout_viewpager.*
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.presentation.fragment.BaseFragment
import net.ihaha.sunny.base.viewModels.IBaseViewModel

abstract class BaseViewPagerFragment<out VM : IBaseViewModel> : BaseFragment<VM>() {

    //region variable
    abstract val pages: List<BaseFragment<*>>

    protected open val viewPagerLayout: ViewPager2 get() = viewPager as ViewPager2
    protected open val viewPagerOrientation: Int get() = ViewPager2.ORIENTATION_HORIZONTAL
    //endregion

    //region override

    //endregion
    override val layoutId: Int get() = R.layout.layout_viewpager

    override fun initLayout() {
        viewPagerLayout.orientation = viewPagerOrientation
        initViewPagerAdapter()
    }

    protected open fun initViewPagerAdapter() {
        viewPagerLayout.adapter = createViewPagerAdapter()
    }

    protected open fun createViewPagerAdapter(): RecyclerView.Adapter<*> {
        return BaseViewPagerFragmentAdapter(pages, this)
    }

    //region method
    open class BaseViewPagerFragmentAdapter(private val adapterPages: List<BaseFragment<*>>, fragment: Fragment) : FragmentStateAdapter(fragment) {
        override fun getItemCount(): Int = adapterPages.size
        override fun createFragment(position: Int): Fragment = adapterPages[position]
    }
    //endregion
}