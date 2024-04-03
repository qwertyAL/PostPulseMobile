package com.example.prodapp

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont

object Constants {

    val provider = GoogleFont.Provider(
        providerAuthority = "com.google.android.gms.fonts",
        providerPackage = "com.google.android.gms",
        certificates = R.array.com_google_android_gms_fonts_certs
    )

    val fontBold = FontFamily(Font(googleFont = GoogleFont("Noto Sans"), fontProvider = provider, weight = FontWeight.Bold))
    val fontRegular = FontFamily(Font(googleFont = GoogleFont("Noto Sans"), fontProvider = provider, weight = FontWeight.Normal))
    val fontMedium = FontFamily(Font(googleFont = GoogleFont("Noto Sans"), fontProvider = provider, weight = FontWeight.Medium))
    val fontBlack = FontFamily(Font(googleFont = GoogleFont("Noto Sans"), fontProvider = provider, weight = FontWeight.Black))

}