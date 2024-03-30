package com.example.prodapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.prodapp.ui.theme.ProdappTheme
import com.example.prodapp.viewmodel.LoginViewModel

@Composable
fun ProfileScreen() {
    val vm = LoginViewModel()

    LoginView(vm)
}

@Composable
fun LoginView(vm: LoginViewModel) {

    Column(
        Modifier
            .padding(start = 24.dp, end = 24.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Вход",
            modifier = Modifier.padding(bottom = 4.dp)
        )

        TextField(
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth(), label = {
                          Text(text = "Login")
        }, value = "", onValueChange = {
            vm.putLogin(it)
        },
            placeholder = {
                Text("Login")
            },
            singleLine = true)

        TextField(
            modifier = Modifier.padding(bottom = 4.dp).fillMaxWidth(), label = {
            Text(text = "Password")
        }, value = "", onValueChange = {
            vm.putPassword(it)
        },
            placeholder = {
                Text("Password")
            },
            singleLine = true)

        Button(
            modifier = Modifier.fillMaxWidth().height(48.dp),
            onClick = {
                vm.loginUser()
            }) {
            Text(text = "Войти")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProdappTheme {
        ProfileScreen()
    }
}