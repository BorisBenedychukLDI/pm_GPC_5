package com.pasa.parimca.appp

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.net.Uri
import android.net.http.SslError
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.lifecycleScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.pasa.parimca.appp.Utilities2oh7ld.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class WebActivity2oh7ld : AppCompatActivity() {

    private lateinit var wv2oh7ld: WebView
    private lateinit var view2oh7ld: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_activity2oh7ld)
        view2oh7ld = findViewById(R.id.v_web_2oh7ld2)
        setupWebView2oh7ld()
        circleChecker2oh7ld()

    }

    override fun onBackPressed() =
        if (wv2oh7ld.canGoBack() && wv2oh7ld.isFocused) wv2oh7ld.goBack() else super.onBackPressed()

    override fun onActivityResult(
        requestCode2oh7ld: Int,
        resultCode2oh7ld: Int,
        data2oh7ld: Intent?
    ) {
        if (filePathCallBack2oh7ld != null && requestCode2oh7ld == 0) {
            val uriResult2oh7ld =
                if (data2oh7ld == null || resultCode2oh7ld != RESULT_OK) null else data2oh7ld.data
            if (uriResult2oh7ld != null) {
                filePathCallBack2oh7ld?.onReceiveValue(arrayOf(uriResult2oh7ld))
            } else {
                filePathCallBack2oh7ld?.onReceiveValue(arrayOf(uriView2oh7ld))
            }
            filePathCallBack2oh7ld = null
        }
        super.onActivityResult(requestCode2oh7ld, resultCode2oh7ld, data2oh7ld)
    }

    private fun splashAnimation2oh7ld() {
        findViewById<View>(R.id.v_web_2oh7ld2).let { circleWeb2oh7ld ->
            AnimatorSet().play(
                ObjectAnimator.ofPropertyValuesHolder(
                    circleWeb2oh7ld, PropertyValuesHolder.ofFloat(
                        View.SCALE_X, 1f, 0.5f
                    ), PropertyValuesHolder.ofFloat(
                        View.SCALE_Y, 1f, 0.5f
                    )
                ).apply {
                    duration = 500
                    start()
                }
            ).before(
                ObjectAnimator.ofFloat(circleWeb2oh7ld, View.ALPHA, 1f, 0f).apply {
                    duration = 500
                    start()
                }
            ).with(
                ObjectAnimator.ofPropertyValuesHolder(
                    circleWeb2oh7ld, PropertyValuesHolder.ofFloat(
                        View.SCALE_X, 1f, 25f
                    ), PropertyValuesHolder.ofFloat(
                        View.SCALE_Y, 1f, 25f
                    )
                ).apply {
                    duration = 500
                    start()
                }
            )

        }
    }

    private fun returnCircle2oh7ld() {
        findViewById<View>(R.id.v_web_2oh7ld2).run {
            animate().alpha(1f).run { duration = 500 }
            animate().scaleX(1f).run { duration = 500 }
            animate().scaleY(1f).run { duration = 500 }
        }
    }

    private fun circleChecker2oh7ld() {
        lifecycleScope.launch {
            delay(500)
            splashAnimation2oh7ld()
        }
    }

    private fun setupWebView2oh7ld() {
        wv2oh7ld = findViewById(R.id.wv_2oh7ld)
        wv2oh7ld.run {
            settings.run {
                loadWithOverviewMode = true
                displayZoomControls = false
                useWideViewPort = true
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                builtInZoomControls = true
                displayZoomControls = false
                domStorageEnabled = true
                mediaPlaybackRequiresUserGesture = false
            }

            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
            webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(
                    view2oh7ld: WebView?,
                    request2oh7ld: WebResourceRequest?
                ): Boolean {
                    if (!internetChecker2oh7ld()) {
                        lifecycleScope.launch {
                            visibility = View.INVISIBLE
                            returnCircle2oh7ld()
                            delay(500)
                            startActivity(
                                Intent(
                                    this@WebActivity2oh7ld,
                                    InternetActivity2oh7ld::class.java
                                ),
                                ActivityOptionsCompat.makeSceneTransitionAnimation(
                                    this@WebActivity2oh7ld,
                                    this@WebActivity2oh7ld.view2oh7ld,
                                    "view_internet"
                                ).toBundle()
                            )
                            finish()
                        }
                    }
                    val modifiedLinks2oh7ld = listOf("mailto:", "tel:")
                    return when {
                        modifiedLinks2oh7ld.find {
                            request2oh7ld
                                ?.url.toString().startsWith(it)
                        } != null -> {
                            view2oh7ld?.context?.startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    request2oh7ld?.url
                                )
                            )
                            true
                        }
                        else -> false
                    }

                }

                override fun onPageFinished(view2oh7ld: WebView?, url2oh7ld: String?) {
                    getSharedPreferences("SP_2oh7ld", MODE_PRIVATE).edit()
                        .putString("Last_Page_2oh7ld", url2oh7ld ?: return).apply()
                    super.onPageFinished(view2oh7ld, url2oh7ld)
                }

                override fun onReceivedSslError(
                    view2oh7ld: WebView?,
                    handler2oh7ld: SslErrorHandler?,
                    error2oh7ld: SslError?
                ) {
                    handler2oh7ld?.proceed()
                    super.onReceivedSslError(view2oh7ld, handler2oh7ld, error2oh7ld)
                }
            }

            webChromeClient = object : WebChromeClient() {
                override fun onShowFileChooser(
                    webView2oh7ld: WebView?,
                    filePathCallback2oh7lds: ValueCallback<Array<Uri>>?,
                    fileChooserParams2oh7ld: FileChooserParams?
                ): Boolean {
                    checkPermission2oh7ld()
                    filePathCallBack2oh7ld = filePathCallback2oh7lds
                    val captureIntent2oh7ld = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                    if (captureIntent2oh7ld.resolveActivity(packageManager) != null) {
                        val providedFile2oh7ld = createTmpFile2oh7ld()
                        uriView2oh7ld = FileProvider.getUriForFile(
                            this@WebActivity2oh7ld,
                            "${packageName}.provider",
                            providedFile2oh7ld
                        )
                        captureIntent2oh7ld.run {
                            putExtra(MediaStore.EXTRA_OUTPUT, uriView2oh7ld)
                            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                        }
                        val actionIntent2oh7ld = Intent(Intent.ACTION_GET_CONTENT).apply {
                            addCategory(Intent.CATEGORY_OPENABLE)
                            type = "image/*"
                        }
                        val intentChooser2oh7ld = Intent(Intent.ACTION_CHOOSER).apply {
                            putExtra(Intent.EXTRA_INTENT, captureIntent2oh7ld)
                            putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent2oh7ld))
                        }
                        startActivityForResult(intentChooser2oh7ld, 0)
                        return true

                    }
                    return super.onShowFileChooser(
                        webView2oh7ld,
                        filePathCallback2oh7lds,
                        fileChooserParams2oh7ld
                    )
                }
            }
            getSharedPreferences("SP_2oh7ld", MODE_PRIVATE).getString("Last_Page_2oh7ld", null)
                ?.let {
                    loadUrl(it)
                    return@run
                }
            loadUrl(parsedURL2oh7ld ?: return)
        }

        findViewById<SwipeRefreshLayout>(R.id.srl_2oh7ld)?.let {
            it.setOnRefreshListener {
                wv2oh7ld.loadUrl(wv2oh7ld.url ?: return@setOnRefreshListener)
                it.isRefreshing = false
            }
        }
    }

}