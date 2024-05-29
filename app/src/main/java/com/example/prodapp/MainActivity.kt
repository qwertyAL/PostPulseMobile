package com.example.prodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.domain.usecase.GetCookieFromLocalSourceUseCase
import com.example.prodapp.ui.screens.LoginScreen
import com.example.prodapp.ui.screens.newinterface.ChannelScreen
import com.example.prodapp.ui.screens.newinterface.MainScreen
import com.example.prodapp.ui.theme.AppTheme
import com.example.prodapp.viewmodel.ChannelViewModel
import com.example.prodapp.viewmodel.LoginViewModel
import com.example.prodapp.viewmodel.MainViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel by viewModel<MainViewModel>()
    private val channelViewModel by viewModel<ChannelViewModel>()
    private val loginViewModel by viewModel<LoginViewModel>()
    private val getSavedCookieToLocalSourceUseCase by inject<GetCookieFromLocalSourceUseCase>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.loadListChannels()

        setContent {
            AppTheme {
                val navController = rememberNavController()
                val startScreen = if (getSavedCookieToLocalSourceUseCase.execute() != "") { "main_screen" } else { "login_screen" }
                NavHost(navController = navController, startDestination = startScreen) {
                    composable("main_screen") {
                        MainScreen(vm = mainViewModel, navController = navController)
                    }
                    composable(
                        route = "channel_screen" + "/{channelId}",
                        arguments = listOf(navArgument("channelId") { type = NavType.LongType }),
                        enterTransition = {
                            slideInHorizontally(
                                initialOffsetX = { 600 }, // small slide 300px
                                animationSpec = tween(
                                    durationMillis = 200,
                                    easing = LinearEasing // interpolator
                                )
                            )
                        },
                        exitTransition = {
                            slideOutHorizontally(
                                targetOffsetX = { 600 },
                                animationSpec = tween(
                                    durationMillis = 200,
                                    easing = LinearEasing
                                )
                            )
                        }) { stackEntry ->
                        val channelId = stackEntry.arguments?.getLong("channelId")

                        ChannelScreen(channelId = channelId, navController = navController, vm = channelViewModel)
                    }
                    composable("login_screen") {
                        LoginScreen(vm = loginViewModel)
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