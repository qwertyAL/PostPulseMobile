package com.example.prodapp.screens

import android.content.res.Resources.Theme
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.PointerIcon.Companion.Text
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.core.content.res.ResourcesCompat.ThemeCompat
import com.example.domain.model.PublicationModel
import com.example.prodapp.Constants

@Composable
fun FullInfoDialog(item: PublicationModel) {

    Dialog(onDismissRequest = {  }) {
        Card(
            modifier = Modifier
                .width(350.dp)
                .wrapContentHeight()
                .background(color = MaterialTheme.colorScheme.background)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {


                Text(text = item.comment,
                    Modifier
                        .background(color = Color.Blue, shape = RoundedCornerShape(4.dp))
                        .padding(4.dp),
                    color = Color.White,
                    fontFamily = Constants.fontMedium
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = item.name)

                AndroidView(modifier = Modifier.fillMaxWidth(), factory = {
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

                if(item.scheduledAt == null || item.scheduledAt == "null") {
                    Text(text = "Не готово к отправке")
                } else {
                    Text(text = "Будет отправлено: ${item.scheduledAt}")
                }
            }
        }
    }
}

@Preview
@Composable
fun DialogPreview() {
    FullInfoDialog(item = PublicationModel(0, 0, "ajflajfjeajliwajdjawdjlajjawli", "test", "3248032", "", "TITLE"))
}