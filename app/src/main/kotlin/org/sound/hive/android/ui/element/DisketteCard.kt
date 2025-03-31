package org.sound.hive.android.ui.element

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.*
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.*
import androidx.compose.ui.res.*
import androidx.compose.ui.text.font.*
import androidx.compose.ui.text.style.*
import androidx.compose.ui.tooling.preview.*
import androidx.compose.ui.unit.*
import org.sound.hive.android.R

@Preview
@Composable
fun DisketteCard(modifier: Modifier = Modifier) {
    val borderColor = colorResource(R.color.black)
    val parentBackgroundColor = colorResource(R.color.white)

    DisketteCardContainer(
        modifier = modifier,
        borderColor = borderColor
    ) {
        AlbumArtImage()
        DisketteHoles(
            parentBackgroundColor = parentBackgroundColor,
            borderColor = borderColor
        )
        AlbumInfoText()
    }
}

@Composable
private fun DisketteCardContainer(
    modifier: Modifier = Modifier,
    borderColor: Color,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = modifier
            .size(
                width = 300.dp,
                height = 250.dp
            )
            .clip(RoundedCornerShape(16.dp))
            .background(colorResource(R.color.saffronYellow))
            .border(
                width = 3.dp,
                color = borderColor,
                shape = RoundedCornerShape(16.dp)
            ),
        content = content
    )
}


@Composable
private fun BoxScope.AlbumArtImage() {
    Image(
        painter = painterResource(id = R.drawable.panchiko),
        contentDescription = "Album Art",
        modifier = Modifier
            .size(110.dp)
            .padding(8.dp)
            .align(Alignment.BottomStart)
            .clip(RoundedCornerShape(16.dp))
            .rotate(-90f),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BoxScope.DisketteHoles(
    parentBackgroundColor: Color,
    borderColor: Color
) {
    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .align(Alignment.Center)
    ) {
        drawCenterHole(parentBackgroundColor, borderColor)
        drawRectangularHole(parentBackgroundColor, borderColor)
    }
}

private fun DrawScope.drawCenterHole(
    backgroundColor: Color,
    borderColor: Color
) {
    drawCircle(
        color = backgroundColor,
        radius = size.minDimension / 10,
    )

    drawCircle(
        color = borderColor.copy(alpha = 0.7f),
        style = Stroke(width = 3.dp.toPx()),
        radius = size.width / 12,
    )
}

private fun DrawScope.drawRectangularHole(
    backgroundColor: Color,
    borderColor: Color
) {
    val rectHeight = size.height / 15
    val innerWidth = size.width / 4
    val innerSize = Size(innerWidth, rectHeight)
    val innerTopLeft = Offset(size.width / 1.4f, size.height / 2 - rectHeight / 2)
    val borderWidth = 3.dp.toPx()

    drawRoundRect(
        color = borderColor.copy(alpha = 0.7f),
        topLeft = innerTopLeft - Offset(
            x = borderWidth - 6,
            y = borderWidth - 6
        ),
        size = Size(
            width = innerSize.width + borderWidth - 4,
            height = innerSize.height + borderWidth - 4
        ),
        style = Stroke(width = borderWidth),
        cornerRadius = CornerRadius(20f)
    )

    drawRoundRect(
        color = backgroundColor,
        topLeft = innerTopLeft,
        size = innerSize,
        cornerRadius = CornerRadius(20f)
    )
}

@Composable
private fun BoxScope.AlbumInfoText() {
    Box(
        modifier = Modifier.padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = "Pink Floyd",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
            )

            Text(
                text = "The black side of the moon The black side of the moon The black side of the moon",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )
        }
    }
}
