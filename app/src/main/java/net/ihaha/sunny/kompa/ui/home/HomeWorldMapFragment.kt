package net.ihaha.sunny.kompa.ui.home

import android.os.Bundle
import net.ihaha.sunny.base.presentation.webview.BaseWebViewFragment
import net.ihaha.sunny.kompa.Constants

class HomeWorldMapFragment : BaseWebViewFragment<HomeWorldMapViewModel>() {

    override fun initBinding() {}

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        loadUrl(Constants.WEB_URL)
//        loadResource(resource())
    }

//    val header : String = "javascript:(function() { " + "var head = document.getElementsByClassName('box confirmed confirmed-box')[0];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header : String = "javascript:(function() { " + "var head = document.getElementsByClassName('confirmed-box')[0].style.display='none';" + "})()"
    private val header1 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('box row-mob col-md-3')[0];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header2 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('row-mob col-md-2')[1];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header3 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('contact-link')[0];"+ "head.parentNode.removeChild(head);" + "})()"
    private val header4 : String = "javascript:(function() { " + "var head = document.getElementsByClassName('ReactModalPortal')[0];"+ "head.parentNode.removeChild(head);" + "})()"

    private fun resource() = arrayOf(header, header1, header2, header3, header4)

    companion object{
        fun newInstance() = HomeWorldMapFragment()
    }




}