package com.example.prodapp.ui.elements

import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.PublicationModel
import com.example.prodapp.R
import com.example.prodapp.ui.screens.FullInfoDialog
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCard(publication: PublicationModel, deleteUnit: () -> Unit) {

    val openDialog = remember {
        mutableStateOf(false)
    }
    if(openDialog.value) {
        FullInfoDialog(item = publication)
    }

    if(publication.scheduledAt != null && publication.scheduledAt != "null") {
        val show = remember {
            mutableStateOf(true)
        }
        val dismissState = rememberDismissState(
            confirmValueChange = {
                if (it == DismissValue.DismissedToStart || it == DismissValue.DismissedToEnd) {
                    show.value = false
                    true
                } else false
            }, positionalThreshold = { 150.dp.toPx() }
        )

//    AnimatedVisibility() {
//
//    }

        AnimatedVisibility(
            visible = show.value,
            exit = fadeOut(spring())
        ) {
            SwipeToDismiss(
                state = dismissState,
                modifier = Modifier,
                background = {
                    PostCardDismiss(dismissState)
                },
                dismissContent = {
                    Column(
                        modifier = Modifier
                            .background(color = Color(0xFFF6F6F6), shape = RoundedCornerShape(10.dp))
                            .fillMaxWidth()
                            .padding(4.dp)
                            .clickable {
                                openDialog.value = true
                            }
                    ) {
                        Row {
                            Text(modifier = Modifier.weight(1F), text = publication.name, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = publication.comment, color = Color(0xFF007AFF))
                        }
                        Text(text = "description", maxLines = 5, overflow = TextOverflow.Ellipsis, color = Color(color = 0xFF909092))
                        Row {
                            Spacer(modifier = Modifier.weight(1F))
                            Log.i("TEST TIME", publication.scheduledAt.toString())
                            Text(text = if(publication.scheduledAt == "null" || publication.scheduledAt == null) {"Не запланировано"} else {publication.scheduledAt!!}, color = Color(0xFF909092))
                        }
                    }
                }
            )
        }

        LaunchedEffect(show.value) {
            if (!show.value) {
                delay(800)
                deleteUnit()
                Log.i("TEST DEL", "del")
//            onRemove(currentItem)
//            Toast.makeText(context, "Item removed", Toast.LENGTH_SHORT).show()
            }
        }
    } else {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFF6F6F6), shape = RoundedCornerShape(10.dp))
                .fillMaxWidth()
                .padding(4.dp)
                .clickable {
                    openDialog.value = true
                }
        ) {
            Row {
                Text(modifier = Modifier.weight(1F), text = publication.name, fontSize = 15.sp, fontWeight = FontWeight.SemiBold, maxLines = 1, overflow = TextOverflow.Ellipsis)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = publication.comment, color = Color(0xFF007AFF))
            }
            Text(text = "description", maxLines = 5, overflow = TextOverflow.Ellipsis, color = Color(color = 0xFF909092))
            Row {
                Spacer(modifier = Modifier.weight(1F))
                Text(text = if(publication.scheduledAt == "null" || publication.scheduledAt == null) {"Не запланировано"} else {publication.scheduledAt!!}, color = Color(0xFF909092))
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostCardDismiss(dismissState: DismissState) {
    val color = Color(0xFFFF1744)
    val direction = dismissState.dismissDirection

    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color, shape = RoundedCornerShape(10.dp))
            .padding(12.dp, 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            Icons.Default.Delete,
            contentDescription = "delete"
        )
        Spacer(modifier = Modifier)
    }
}

@Composable
@Preview
fun PostCardPreview() {
//    PostCard(PublicationModel(0, 0, "awdad", "wadwdwa", "adw", "wda", "adwawd", ""))
}