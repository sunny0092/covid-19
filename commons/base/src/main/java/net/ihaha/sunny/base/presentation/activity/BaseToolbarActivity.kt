package net.ihaha.sunny.base.presentation.activity

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.databinding.ViewDataBinding
import kotlinx.android.synthetic.main.layout_toolbar.*
import net.ihaha.sunny.base.utils.extensions.hide
import net.ihaha.sunny.base.utils.extensions.show
import net.ihaha.sunny.utils.Constants
import kotlin.reflect.jvm.internal.impl.load.java.Constant

abstract class BaseToolbarActivity< T : ViewDataBinding> : BaseActivity<T>() {

    //region variable

    protected open val centerToolbarTitle: Boolean = false
    protected open val toolbarLogo: Drawable? = null
    protected open val toolBarTitle: String? = null

    protected abstract fun initUI(savedInstanceState: Bundle?)

    //endregion

    //region override
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolBar()
        initUI(savedInstanceState)
    }

    //endregion

    //region method

    private fun setupToolBar() {
        toolbarLayout?.let { toolbar ->
            if (toolBarTitle?.isNotEmpty()!!) {
                setToolbarLogo()
                setToolbarTitle()
            }
            setSupportActionBar(toolbar)
        }
    }

    private fun setToolbarLogo(){
        if(toolbarLogo != null){
            iv_toolbar_logo.show()
            iv_toolbar_logo.setBackgroundDrawable(toolbarLogo)
        }else{
            iv_toolbar_logo.hide()
        }
    }

    private fun setToolbarTitle(){
        if (centerToolbarTitle) {
            tv_toolbar_title?.show()
            tv_toolbar_title?.text = toolBarTitle
        } else {
            tv_toolbar_title?.hide()
            toolbarLayout?.title = toolBarTitle
        }
    }





    //endregion

}