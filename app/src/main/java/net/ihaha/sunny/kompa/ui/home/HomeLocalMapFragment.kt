package net.ihaha.sunny.kompa.ui.home

import android.os.Bundle
import net.ihaha.sunny.base.presentation.webview.BaseWebViewFragment
import net.ihaha.sunny.kompa.Constants

class HomeLocalMapFragment : BaseWebViewFragment<HomeLocalMapViewModel>() {

    override fun initBinding() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadUrl(Constants.WEB_URL)
//        loadResource(resource())
    }

    private val header : String = "javascript:(function() { " + "var head = document.getElementsByClassName('box confirmed')[1];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header1 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('box main-box col-md-5')[0];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header2 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('row-mob col-md-2')[1];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header3 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('contact-link')[0];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header4 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('ReactModalPortal')[0];"+ "head.parentNode.removeChild(head);" + "})()"

    private fun resource() = arrayOf(header, header1, header2, header3, header4)

    companion object{
        fun newInstance() = HomeLocalMapFragment()
    }




}