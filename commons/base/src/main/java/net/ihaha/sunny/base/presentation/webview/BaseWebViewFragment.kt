package net.ihaha.sunny.base.presentation.webview

import android.annotation.SuppressLint
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import androidx.databinding.DataBindingUtil
import net.ihaha.sunny.base.R
import net.ihaha.sunny.base.databinding.LayoutWebviewBinding
import net.ihaha.sunny.base.presentation.fragment.BaseFragment
import net.ihaha.sunny.base.viewModels.IBaseViewModel

abstract class BaseWebViewFragment<out VM: IBaseViewModel> : BaseFragment<VM>() {


    override val layoutId: Int get() = R.layout.layout_webview

    protected open var binding: LayoutWebviewBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return DataBindingUtil.inflate<LayoutWebviewBinding>(inflater, layoutId,  container, false).also{
            binding = it
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initSettings()
        initWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initSettings() {
        binding?.webView?.settings?.javaScriptEnabled = true
        binding?.webView?.settings?.domStorageEnabled = true
        binding?.webView?.settings?.allowFileAccess = true
        binding?.webView?.settings?.javaScriptCanOpenWindowsAutomatically = true
        binding?.webView?.settings?.loadsImagesAutomatically = true
        binding?.webView?.settings?.databaseEnabled = true
//        webView.settings.setGeolocationEnabled(true)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            binding?.webView?.settings?.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        }
    }

    private fun initWebView() {
        binding?.webView?.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(p0: View?, keyCode: Int, p2: KeyEvent?): Boolean {
                if (keyCode == KeyEvent.KEYCODE_BACK && binding?.webView?.canGoBack()!!) {
                    binding?.webView?.goBack()
                    return true
                }
                return false
            }
        })
        binding?.webView?.webViewClient = BaseWebViewClient()
        binding?.webView?.webChromeClient = BaseWebChromeClient()
    }

    open fun loadUrl(url: String) {
//        if (!NetworkUtils.isConnected(this)) {
//            setPageError()
//        } else {
        binding?.webView?.loadUrl(url)
//        }
    }

    private var resource : Array<String>? = null

    open fun loadResource(resource: Array<String>){
        this.resource = resource
    }

    open inner class BaseWebViewClient : WebViewClient() {

        override fun onLoadResource(view: WebView?, url: String?) {
            super.onLoadResource(view, url)
            if(resource != null) {
                for (i in resource!!){
                    binding?.webView?.loadUrl(i)
                }
            }
        }

        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest?): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                binding?.webView?.loadUrl(request?.url.toString())
            } else {
                binding?.webView?.loadUrl(request?.toString())
            }
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
//            setPageEndLoading()
        }

        override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
//            setPageError()
        }

        override fun onReceivedSslError(view: WebView, handler: SslErrorHandler, error: SslError) {
            handler.proceed()
        }

    }

    open inner class BaseWebChromeClient : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == 100) {
                binding?.progressBar?.visibility = View.GONE
//                setPageEndLoading()
            } else {
                if (binding?.progressBar?.visibility != View.VISIBLE) binding?.progressBar?.visibility = View.VISIBLE
                binding?.progressBar?.progress = newProgress
            }
        }

//        override fun onGeolocationPermissionsShowPrompt(origin: String?, callback: GeolocationPermissions.Callback?) {
//            callback?.invoke(origin, true, false)
//            super.onGeolocationPermissionsShowPrompt(origin, callback)
//        }
    }

    override fun onResume() {
        super.onResume()
        binding?.webView?.onResume()
        binding?.webView?.resumeTimers()
    }

    override fun onPause() {
        super.onPause()
        binding?.webView?.onPause()
        binding?.webView?.pauseTimers()
    }

    override fun onDestroyView() {
        binding?.webView?.clearCache(true)
        binding?.webView?.clearFormData()
        binding?.webView?.clearHistory()
        binding?.webView?.stopLoading()
        binding?.webView?.removeAllViews()
        binding?.webView?.destroy()
        binding?.unbind()
        binding = null
        super.onDestroyView()
    }
}