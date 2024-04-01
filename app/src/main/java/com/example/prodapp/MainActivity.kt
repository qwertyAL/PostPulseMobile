package com.example.prodapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.example.data.api.Api
import com.example.prodapp.screens.LoginScreen
import com.example.prodapp.screens.MainScreen
import com.example.prodapp.ui.theme.ProdappTheme
import com.example.prodapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
            Log.i("TEST_CHANNELS", Api().getListChannels().toString())
//        }

        setContent {
            ProdappTheme {
                MainScreen(mainViewModel)
//                LoginScreen()
            }
        }
    }
}


@Preview
@Composable
fun PreviewLogin() {
    ProdappTheme {
        LoginScreen()
    }
}