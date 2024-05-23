package com.example.prodapp.screens.newinterface

import android.provider.MediaStore.Images
import android.widget.ImageButton
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ChannelScreen() {
    Column(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF))
            .fillMaxSize()
    ) {
        TopBar()
    }
}

@Composable
fun TopBar() {
    Column(
        modifier = Modifier.background(color = Color(0xFFF6F6F6)),
    ) {
        Row(
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp, top = 8.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .width(20.dp)
                    .height(20.dp)
            ) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = Color(0xFF909092))
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(imageVector = Icons.Default.AccountCircle, contentDescription = null, modifier = Modifier
                .width(40.dp)
                .height(40.dp))
            Spacer(modifier = Modifier.width(4.dp))
            Column {
                Text(text = "Channel name", fontSize = 15.sp, fontWeight = FontWeight.SemiBold)
                Text(text = "5 users", color = Color(0xFF909092), fontSize = 15.sp)
            }
            Spacer(modifier = Modifier.weight(1F))
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Default.MoreVert, contentDescription = null, tint = Color(0xFF909092))
            }
        }
        var tabItem by rememberSaveable {
            mutableIntStateOf(0)
        }
        TabRow(
            modifier = Modifier.fillMaxWidth(),
            selectedTabIndex = tabItem,
            tabs = {
                Tab(
                    modifier = Modifier.background(Color(0xFFF6F6F6)),
                    selected = tabItem == 0,
                    onClick = {
                        tabItem = 0
//                        vm.loadPublications(channelId, tabItem)
                    },
                    text = {
                        Text(text = "All", fontWeight = FontWeight.Medium, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    selectedContentColor = Color(0xFF007AFF),
                    unselectedContentColor = Color(0xFF909092)
                )
                Tab(
                    modifier = Modifier.background(Color(0xFFF6F6F6)),
                    selected = tabItem == 1,
                    onClick = {
                        tabItem = 1
//                        vm.loadPublications(channelId, tabItem)
                    },
                    text = {
                        Text(text = "Postponed", fontWeight = FontWeight.Medium, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    selectedContentColor = Color(0xFF007AFF),
                    unselectedContentColor = Color(0xFF909092)
                )
                Tab(
                    modifier = Modifier.background(Color(0xFFF6F6F6)),
                    selected = tabItem == 2,
                    onClick = {
                        tabItem = 2
//                        vm.loadPublications(channelId, tabItem)
                    },
                    text = {
                        Text(text = "Sent", fontWeight = FontWeight.Medium, fontSize = 15.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
                    },
                    selectedContentColor = Color(0xFF007AFF),
                    unselectedContentColor = Color(0xFF909092)
                )
                Tab(
                    modifier = Modifier.background(Color(0xFFF6F6F6)),
                    selected = tabItem == 2,
                    onClick = {
                        tabItem = 2
//                        vm.loadPublications(channelId, tabItem)
                    },
                    text = {
                        Text(
                            text = "Drafts",
                            fontWeight = FontWeight.Medium,
                            fontSize = 15.sp,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    },
                    selectedContentColor = Color(0xFF007AFF),
                    unselectedContentColor = Color(0xFF909092)
                )
            }
        )
    }
}

@Composable
@Preview
fun PreviewChannelScreen() {
    ChannelScreen()
}