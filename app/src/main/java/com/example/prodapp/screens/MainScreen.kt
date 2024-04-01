package com.example.prodapp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Create
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.PublicationModel
import com.example.prodapp.viewmodel.MainViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(vm: MainViewModel) {

    vm.loadListChannels()
    vm.loadListAllDraftPublications()
    vm.loadListAllSentPublications()
    vm.loadListAllSchedulePublications()
    val items by vm.listChannels.observeAsState()

    Surface(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth(),
        color = MaterialTheme.colorScheme.background
    ) {
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        var selectedItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        var selectedTabItemIndex by rememberSaveable {
            mutableIntStateOf(0)
        }
        val listPublications by vm.publications.observeAsState()
        vm.loadAllPublication(0)
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Spacer(modifier = Modifier.height(16.dp))
                    items?.forEachIndexed { index, item ->
                        NavigationDrawerItem(
                            label = {
                                Text(text = item.name)
                            },
                            selected = index == selectedItemIndex,
                            onClick = {
                                selectedItemIndex = index
                                scope.launch {
                                    drawerState.close()
                                }
                            },
                            icon = {
                                Icon(
                                    imageVector = if(index == selectedItemIndex) {
                                        Icons.Filled.Create
                                    } else Icons.Outlined.Create,
                                    contentDescription = item.name
                                )
                            },
//                            badge = {
//                                item.badgeCount?.let {
//                                    Text(text = item.badgeCount.toString())
//                                }
//                            },
                            modifier = Modifier
                                .padding(NavigationDrawerItemDefaults.ItemPadding))
                    }
                }
            },
            drawerState = drawerState
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        modifier = Modifier.fillMaxWidth(),
                        title = {
                            Text(text = items?.get(selectedItemIndex)?.name ?: "")
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch {
                                    drawerState.open()
                                }
                            } ) {
                                Icon(imageVector = Icons.Default.Menu, contentDescription = "Menu")
                            }
                        }
                    )
                }
            ) { innerPadding ->
                Column(
                    Modifier.padding(innerPadding)
                ) {
                    TabRow(
                        modifier = Modifier.fillMaxWidth(),
                        selectedTabIndex = selectedTabItemIndex,
                        tabs = {
                            Tab(
                                selected = selectedTabItemIndex == 0,
                                onClick = {
                                    selectedTabItemIndex = 0
                                    vm.loadAllPublication(0)
                                },
                                text = { Text(text = "Отложенные", maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 10.sp) },
                                icon = { Icon(imageVector = Icons.Default.DateRange, contentDescription = null) }
                            )
                            Tab(
                                selected = selectedTabItemIndex == 1,
                                onClick = {
                                    selectedTabItemIndex = 1
                                    vm.loadAllPublication(1)
                                },
                                text = { Text(text = "Черновики", maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 10.sp) },
                                icon = { Icon(imageVector = Icons.Default.Create, contentDescription = null) }
                            )
                            Tab(
                                selected = selectedTabItemIndex == 2,
                                onClick = {
                                    selectedTabItemIndex = 2
                                    vm.loadAllPublication(2)
                                },
                                text = { Text(text = "Отправленные", maxLines = 1, overflow = TextOverflow.Ellipsis, fontSize = 10.sp) },
                                icon = { Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null) }
                            )
                        }
                    )
                    ListPublications(listPublications = listPublications.orEmpty())
                }
            }
        }
    }

}

@Composable
fun TabBar() {

}

@Preview
@Composable
fun TabPreview() {
    TabBar()
}

@Composable
fun ListPublications(listPublications: List<PublicationModel>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(16.dp)
    ) {
        listPublications.forEach {
            item { ItemPublication(item = it) }
        }
    }
}

@Composable
fun ItemPublication(item: PublicationModel) {
    Surface(
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = item.title,
                fontSize = 16.sp
            )
            Row(
                modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
            ) {
                Text(text = "31.03.2024 13:00", fontSize = 10.sp, color = Color.DarkGray)
//                Text(text = , fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
            }
            Row(
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                Text(text = "Приоритет: Высокий", fontSize = 10.sp, color = Color.DarkGray)
//                Text(text = "", fontSize = 12.sp, modifier = Modifier.padding(start = 4.dp))
            }
            Text(text = item.text, maxLines = 3, fontSize = 10.sp)
        }
    }
}