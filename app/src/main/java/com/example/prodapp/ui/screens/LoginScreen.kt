package com.example.prodapp.ui.screens

import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import com.example.prodapp.JavascriptInterface
import com.example.prodapp.viewmodel.LoginViewModel

@Composable
fun LoginScreen(vm: LoginViewModel) {

    val html = """
        <body style="width: 100%; height: 100%; display: flex; justify-content: center; align-items: center">
            <iframe src="http://post-pulse.ru/mobile_auth"></iframe>
        </body>
    """.trimMargin()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
        return@AndroidView WebView(it).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            addJavascriptInterface(JavascriptInterface(vm), "mobileAuthDataReceiver")

//            loadData(html, "text/html", "utf-8")
            loadUrl("http://post-pulse.ru/mobile_auth")
        }
    })

}