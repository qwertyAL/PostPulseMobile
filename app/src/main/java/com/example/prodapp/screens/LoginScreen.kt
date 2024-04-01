package com.example.prodapp.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun LoginScreen() {

    val html = """
        <body>
            <p>Test</p>
        </body>
        
        <script async src="https://telegram.org/js/telegram-widget.js?22" data-telegram-login="PostPulsePRODBot" data-size="large" data-onauth="onTelegramAuth(user)" data-request-access="write"></script>
        <script type="text/javascript">
          function onTelegramAuth(user) {
            alert('Logged in as ' + user.first_name + ' ' + user.last_name + ' (' + user.id + (user.username ? ', @' + user.username : '') + ')');
          }
        </script>
       
    """.trimMargin()

    AndroidView(
        modifier = Modifier.fillMaxSize(),
        factory = {
        return@AndroidView WebView(it).apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()

            loadData(html, "text/html", "utf-8")
        }
    })

}