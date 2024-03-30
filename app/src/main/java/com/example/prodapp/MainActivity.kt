package com.example.prodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.prodapp.screens.ProfileScreen
import com.example.prodapp.ui.theme.ProdappTheme
import com.example.prodapp.viewmodel.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val pvm = ProfileViewModel()
        val id = pvm.id.value

        setContent {
            ProdappTheme {
                if(id == -1) {
                    ProfileScreen()
                } else {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {

}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProdappTheme {
        ProfileScreen()
    }
}