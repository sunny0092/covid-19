package net.ihaha.sunny.kompa.ui.news

import android.os.Bundle
import net.ihaha.sunny.base.presentation.fragment.BaseBindingFragment
import net.ihaha.sunny.kompa.databinding.FragmentNewsBinding
import net.ihaha.sunny.kompa.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : BaseBindingFragment<FragmentNewsBinding, NewsViewModel>() {

    //region override

    override val viewModel: NewsViewModel by viewModel()

    override val layoutId: Int = R.layout.fragment_news

    //endregion

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    companion object{
        fun newInstance() = NewsFragment()
    }

    override fun initBinding() {
        TODO("Not yet implemented")
    }

}