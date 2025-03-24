package org.sound.hive.android.ui.element

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.sound.hive.android.R
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun DisketteCard(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(250.dp, 300.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.saffronYellow))
            .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
    ) {
        Image(
            painter = painterResource(id = R.drawable.panchiko),
            contentDescription = "Album Art",
            modifier = Modifier
                .size(110.dp)
                .padding(8.dp)
                .align(Alignment.TopStart)
        )

        Canvas(
            modifier = Modifier
                .size(60.dp)
                .align(Alignment.Center)
        ) {
            drawCircle(Color.Gray)
        }

        Box(
            modifier = Modifier
                .width(15.dp)
                .height(50.dp)
                .background(Color.Black)
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp)
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "The black side of the moon",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .rotate(90f)
                        .padding(bottom = 20.dp)
                        .offset(x = (85).dp, y = (-50).dp)
                )

                Text(
                    text = "Pink Floyd",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .rotate(90f)
                        .offset(x = (5).dp, y = (-50).dp)
                )
            }
        }
    }
}
