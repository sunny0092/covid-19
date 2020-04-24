package net.ihaha.sunny.base.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import net.ihaha.sunny.base.viewModels.IBaseViewModel
import net.ihaha.sunny.base.BR

abstract class BaseBindingFragment<DB : ViewDataBinding, out VM : IBaseViewModel> : BaseFragment<VM>() {

    protected open var binding: DB? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<DB>(inflater, layoutId,  container, false).also{
            binding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.setVariable(BR.vm, viewModel)
        }.root
    }

    override fun onDestroyView() {
        binding?.unbind()
        binding = null
        super.onDestroyView()
    }
}