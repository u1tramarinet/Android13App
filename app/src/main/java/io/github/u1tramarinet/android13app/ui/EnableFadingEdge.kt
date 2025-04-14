package io.github.u1tramarinet.android13app.ui

import android.R.attr.text
import android.annotation.SuppressLint
import android.util.Log
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView

fun Modifier.enableFadingEdge(): Modifier {
    return this
        .graphicsLayer(compositingStrategy = CompositingStrategy.Offscreen)
        .drawWithContent {
            drawContent()
            drawRect(
                brush = Brush.horizontalGradient(
                    0f to Color.Black,
                    1f to Color.Transparent,
                    startX = size.width - 33,
                    endX = size.width,
                ),
                blendMode = BlendMode.DstIn
            )
        }
}

@SuppressLint("SetTextI18n")
@Composable
private fun AndroidTextView(modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            val view = TextView(context).apply {
                isHorizontalFadingEdgeEnabled = true
                isSingleLine = true
                setTextColor(android.graphics.Color.BLACK)
                textSize = 36f
            }

            view.text = "${view.horizontalFadingEdgeLength}${"TEST".repeat(11)}"
            view
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun EnableFadingEdgePreview() {
    Column {
        Column(
            modifier = Modifier
                .width(100.dp)
                .background(color = Color.White)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().enableFadingEdge(),
                text = "33"+"TEST".repeat(10),
                maxLines = 1,
                fontSize = 36.sp,
                overflow = TextOverflow.Clip,
            )
            AndroidTextView()
        }
        Column(
            modifier = Modifier
                .width(200.dp)
                .background(color = Color.White)
        ) {
            Text(
                modifier = Modifier.fillMaxWidth().enableFadingEdge(),
                text = "33"+"TEST".repeat(10),
                maxLines = 1,
                fontSize = 36.sp,
                overflow = TextOverflow.Clip,
            )
            AndroidTextView()
        }
    }
}