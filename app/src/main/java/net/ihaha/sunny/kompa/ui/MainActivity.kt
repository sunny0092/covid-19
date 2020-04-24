package net.ihaha.sunny.kompa.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import kotlinx.android.synthetic.main.activity_main.*
import net.ihaha.sunny.base.presentation.activity.BaseToolbarActivity
import net.ihaha.sunny.base.utils.extensions.slideDown
import net.ihaha.sunny.base.utils.extensions.slideUp
import net.ihaha.sunny.base.utils.navigation.setupWithNavController
import net.ihaha.sunny.kompa.Constants
import net.ihaha.sunny.kompa.R
import net.ihaha.sunny.kompa.databinding.ActivityMainBinding

class MainActivity : BaseToolbarActivity<ActivityMainBinding>() {

    //region variable
    private var currentNavController: LiveData<NavController>? = null
    //endregion

    //region set and get
    private val navGraphId = listOf(
            R.navigation.navigation_home,
            R.navigation.navigation_news,
            R.navigation.navigation_guide,
            R.navigation.navigation_prevention,
            R.navigation.navigation_setting
        )
    //endregion

    //region override
    override val layoutId: Int get() = R.layout.activity_main
    override val centerToolbarTitle: Boolean get() = true
    override val toolBarTitle: String? get() = Constants.APP
    override val toolbarLogo: Drawable? get() = resources.getDrawable(R.drawable.ic_logo_kompa)

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

    override fun initUI(savedInstanceState: Bundle?) {
        if(savedInstanceState == null){
            setupBottomNavigationBar()
        }
    }
    //endregion

    //region method
    private fun initComponents(){

    }

    private fun initEventListeners(){

    }

    private fun setupBottomNavigationBar(){
        initNavController()
        initNavControllerListener()
    }

    private fun initNavController(){
        val controller = bottom_navigation.setupWithNavController(
            navGraphIds = navGraphId,
            fragmentManager = supportFragmentManager,
            containerId = R.id.fragment_container,
            intent = intent
        )
        controller?.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)
        })
        currentNavController = controller
    }

    private fun initNavControllerListener(){
        currentNavController?.value?.addOnDestinationChangedListener { _, destination, _ ->

        }
    }

    private fun hideBottomTabs() {
        if (bottom_navigation?.visibility == View.VISIBLE) {
            bottom_navigation?.slideDown()
        }
    }

    private fun showBottomTabs() {
        if (bottom_navigation?.visibility == View.VISIBLE) {
            bottom_navigation?.slideUp()
        }
    }
    //endregion




}