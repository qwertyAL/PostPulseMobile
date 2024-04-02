package com.example.prodapp.screens

import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import com.example.domain.model.PublicationModel

@Composable
fun FullInfoDialog(item: PublicationModel) {

    Dialog(onDismissRequest = {  }) {
        Card(
            modifier = Modifier
                .width(350.dp)
                .padding(16.dp)
                .wrapContentHeight()
        ) {
            Column {
                AndroidView(factory = {
                    return@AndroidView WebView(it).apply {
                        settings.javaScriptEnabled = true
                        webViewClient = WebViewClient()

                        val url = "prod_final_2024/117"

                        val html = """
                                <head>
                                    <meta name="viewport" content="width=device-width, initial-scale=1">
                                </head>
                                <body style="margin: 0; padding: 0">
                                    <script async src="https://telegram.org/js/telegram-widget.js?22" data-telegram-post="$url" data-width="60%" data-userpic="false" data-dark="1"></script>
                                </body>
                            """.trimMargin()

                        loadData(html, "text/html", "utf-8")
                    }
                })
            }
        }
    }
}