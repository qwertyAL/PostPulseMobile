package com.example.prodapp.ui.screens.newinterface

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.liveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.domain.model.ChannelModel
import com.example.prodapp.viewmodel.MainViewModel

@Composable
fun MainScreen(vm: MainViewModel, navController: NavController) {

    vm.loadListChannels()
    val listChannels by vm.listChannels.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFFFFFFF)),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier
                .width(24.dp)
                .height(24.dp))
            Text(
                text = "Channels",
                fontWeight = FontWeight.SemiBold,
                fontSize = 15.sp,
                color = Color(0xFF000000),
                modifier = Modifier
                    .weight(1F),
                textAlign = TextAlign.Center
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(24.dp)
                    .height(24.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, tint = Color(0xFF007AFF))
            }
        }
        SearchView()
        Spacer(modifier = Modifier.height(16.dp))
        ChannelsList(listChannels, navController)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView() {
    SearchBar(
        query = "",
        onQueryChange = {},
        onSearch = {},
        active = false,
        onActiveChange = {},
        shape = RoundedCornerShape(size = 10.dp),
        colors = SearchBarDefaults.colors(containerColor = Color(0xFFEFEFF0)),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 0.dp, bottom = 0.dp),
        leadingIcon = {
            Icon(imageVector = Icons.Default.Search, contentDescription = null, tint = Color(0xFFC5C5C7))
        },
        placeholder = {
            Text(text = "Search", fontSize = 15.sp, fontWeight = FontWeight.Medium, color = Color(0xFFC5C5C7))
        }
    ) {}
}

@Composable
fun SpacerChannels() {
    Row(
        modifier = Modifier.padding(top = 1.dp, bottom = 1.dp)
    ) {
        Spacer(modifier = Modifier.width(32.dp))
        Spacer(modifier = Modifier
            .height(1.dp)
            .background(color = Color(0xFFD9D9D9))
            .weight(1F))
        Spacer(modifier = Modifier.width(32.dp))
    }
}

@Composable
fun ChannelsList(listChannels: List<ChannelModel>?, navController: NavController) {
    LazyColumn {
        item {
            Channel(ChannelModel(id = -1, name = "All channels"), navController)
            SpacerChannels()
        }
        listChannels?.forEach {
            item {
                Channel(channelInfo = it, navController)
                SpacerChannels()
            }
        }
    }
}

@Composable
fun Channel(channelInfo: ChannelModel, navController: NavController) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp)
            .clickable { navController.navigate("channel_screen/${channelInfo.id}") }
    ) {
        if(channelInfo.id == -1L) {
            Icon(imageVector = Icons.Default.List, contentDescription = null, tint = Color(0xFF007AFF), modifier = Modifier
                .width(60.dp)
                .height(60.dp))
        } else {
            Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null, tint = Color(0xFFD9D9D9), modifier = Modifier
                .width(60.dp)
                .height(60.dp))
        }
        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1F)
        ) {
            if(channelInfo.id == -1L) {
                Text(text = "All channels", fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF000000))
            } else {
                Text(text = channelInfo.name, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color(0xFF000000))
            }
            Text(text = "last post name", fontSize = 15.sp, fontWeight = FontWeight.Normal, color = Color(0xFFC5C5C7))
        }
        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(text = "09/29", fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color(0xFFC5C5C7))
            if(channelInfo.id != -1L) {
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .width(20.dp)
                        .height(20.dp)
                ) {
                    Icon(imageVector = Icons.Default.FavoriteBorder, contentDescription = null, tint = Color(0xFFC5C5C7))
                }
            }
        }
    }
}

@Composable
@Preview
fun PreviewMainScreen() {
//    MainScreen(navController = rememberNavController())
}