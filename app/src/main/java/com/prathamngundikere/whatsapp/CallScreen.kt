package com.prathamngundikere.whatsapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.prathamngundikere.whatsapp.ui.theme.helvetica

@Composable
fun CallScreen(
    modifier: Modifier = Modifier
) {
    LazyColumn (
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(10.dp)
    ){
        items(30) {
            CallItem()
        }
    }
}

@Composable
fun CallItem(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ){
            Image(
                painter = painterResource(id = R.drawable.contact_image),
                contentDescription = "image",
                modifier = Modifier.size(50.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "Unknown Contact",
                fontFamily = helvetica,
                fontWeight = FontWeight.Bold,
                fontSize = 21.sp,
                color = Color.Black
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .align(alignment = Alignment.CenterEnd)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.call_unselected),
                contentDescription = "Call",
                tint = Color.Green
            )
        }
    }
}
