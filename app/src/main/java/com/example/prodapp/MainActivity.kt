package com.example.prodapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.data.api.Api
import com.example.domain.usecase.GetCookieFromLocalSourceUseCase
import com.example.prodapp.screens.LoginScreen
import com.example.prodapp.screens.MainScreen
import com.example.prodapp.ui.theme.AppTheme
import com.example.prodapp.viewmodel.LoginViewModel
import com.example.prodapp.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    private val loginViewModel by viewModel<LoginViewModel>()
    private val getSavedCookieToLocalSourceUseCase by inject<GetCookieFromLocalSourceUseCase>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        lifecycleScope.launch {
//            Log.i("TEST_CHANNELS", Api().getListChannels().toString())
//        }


        setContent {
            AppTheme {
                val navController = rememberNavController()
                val startScreen = if(getSavedCookieToLocalSourceUseCase.execute() != "") { "main_screen" } else { "login_screen" }
                NavHost(navController = navController, startDestination = startScreen) {
                    composable("main_screen") {
                        MainScreen(vm = mainViewModel)
                    }
                    composable("login_screen") {
                        LoginScreen(vm = loginViewModel) {
                            navController.navigate("main_screen")
                        }
                    }
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PreviewLogin() {
//    AppTheme {
//        LoginScreen()
//    }
//}