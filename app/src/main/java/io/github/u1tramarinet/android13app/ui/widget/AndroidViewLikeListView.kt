package io.github.u1tramarinet.android13app.ui.widget

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.animateScrollBy
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import kotlinx.coroutines.launch

@Composable
fun AndroidViewLikeListView(
    modifier: Modifier = Modifier,
    scrollState: ScrollState = rememberScrollState(),
    content: @Composable ColumnScope.() -> Unit
) {
    val state = rememberScrollControllerState(scrollState)
    Row(modifier = modifier.fillMaxWidth()) {
        ScrollController(
            modifier = Modifier.width(100.dp),
            state = state,
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .onGloballyPositioned { coordinates ->
                    state.updateColumnVisibleHeight(coordinates.size.height)
                }
                .verticalScroll(scrollState)
                .onGloballyPositioned { coordinates ->
                    state.updateColumnContentHeight(coordinates.size.height)
                },
            content = content,
        )
    }
}

@Composable
private fun ScrollController(modifier: Modifier = Modifier, state: ScrollControllerState) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ArrowButton(
            modifier = Modifier
                .weight(0.1f)
                .aspectRatio(1f),
            onClick = state.clickArrowUp,
            enabled = state.canClickArrowUp,
            icon = Icons.Default.KeyboardArrowUp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        ScrollTrack(
            modifier = Modifier.weight(0.8f),
            state = state,
        )
        Spacer(modifier = Modifier.height(8.dp))
        ArrowButton(
            modifier = Modifier
                .weight(0.1f)
                .aspectRatio(1f),
            onClick = state.clickArrowDown,
            enabled = state.canClickArrowDown,
            icon = Icons.Default.KeyboardArrowDown,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ScrollTrack(modifier: Modifier = Modifier, state: ScrollControllerState) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = Color.Black)
            .pointerInput(state) {
                detectTapGestures(
                    onTap = { offset ->
                        state.clickTrack(offset.y.toInt())
                    }
                )
            }
            .onGloballyPositioned { coordinates ->
                state.updateTrackHeight(coordinates.size.height)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = state.thumbTopPositionDp)
                .scrollable(
                    orientation = Orientation.Vertical,
                    state = rememberScrollableState { delta ->
                        state.dragThumb(delta.toInt())
                        delta
                    }
                ),
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .width(width = 16.dp)
                    .height(height = state.thumbHeightDp)
                    .background(color = Color.White),
            )
        }
    }
}

@Composable
private fun ArrowButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean,
    icon: ImageVector,
) {
    RepeatableButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        contentPadding = PaddingValues(0.dp),
        shape = CircleShape,
    ) {
        Icon(
            modifier = Modifier
                .size(24.dp)
                .padding(0.dp),
            imageVector = icon,
            contentDescription = "",
        )
    }
}

@Composable
private fun rememberScrollControllerState(scrollState: ScrollState): ScrollControllerState {
    val density = LocalDensity.current
    val scrollPositionPx = scrollState.value
    val scrollPositionMaxPx = scrollState.maxValue
    val trackHeightPx = remember { mutableStateOf(0) }
    val columnVisibleHeightPx = remember { mutableStateOf(0) }
    val columnContentHeightPx = remember { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    val trackHeightDp = remember(trackHeightPx.value, density.density) {
        (trackHeightPx.value * density.density).toInt().dp
    }

    val thumbHeightPx = remember(trackHeightDp, columnContentHeightPx.value, scrollPositionMaxPx) {
        val result = if (columnContentHeightPx.value == 0) {
            0
        } else {
            val scrollBottomPaddingPx = columnContentHeightPx.value - scrollPositionMaxPx
            Log.d("ScrollControllerState", "scrollBottomPaddingPx: $scrollBottomPaddingPx")
            if (scrollBottomPaddingPx <= 0) {
                0
            } else {
                (trackHeightPx.value * (scrollBottomPaddingPx.toFloat() / columnContentHeightPx.value.toFloat())).toInt()
            }
        }
        Log.d("ScrollControllerState", "thumbHeightDp: $result")
        result
    }

    val thumbHeightDp = remember(thumbHeightPx, density.density) {
        (thumbHeightPx * density.density).toInt().dp
    }

    val thumbTopPositionLowerLimitDp = remember(trackHeightDp, thumbHeightPx) {
        val result = max(trackHeightDp - thumbHeightDp, 0.dp)
        Log.d("ScrollControllerState", "thumbTopPositionLowerLimitDp: $result")
        result
    }

    return remember(
        scrollPositionPx,
        scrollPositionMaxPx,
        trackHeightPx,
        columnVisibleHeightPx,
        columnContentHeightPx,
        thumbHeightPx,
        thumbHeightDp,
        thumbTopPositionLowerLimitDp,
    ) {
        val scrollRatio = scrollPositionPx.toFloat() / scrollPositionMaxPx.toFloat()

        ScrollControllerState(
            canClickArrowUp = scrollState.canScrollBackward,
            canClickArrowDown = scrollState.canScrollForward,
            thumbTopPositionDp = thumbTopPositionLowerLimitDp * scrollRatio,
            thumbHeightDp = thumbHeightDp,
            updateTrackHeight = { heightPx ->
                if (trackHeightPx.value != heightPx) {
                    Log.d(
                        "ScrollControllerState",
                        "updateTrackHeight(${trackHeightPx.value}->$heightPx)"
                    )
                    trackHeightPx.value = heightPx
                }
            },
            updateColumnVisibleHeight = { heightPx ->
                if (columnVisibleHeightPx.value != heightPx) {
                    Log.d(
                        "ScrollControllerState",
                        "updateColumnVisibleHeight(${columnVisibleHeightPx.value}->$heightPx)"
                    )
                    columnVisibleHeightPx.value = heightPx
                }
            },
            updateColumnContentHeight = { heightPx ->
                if (columnContentHeightPx.value != heightPx) {
                    Log.d(
                        "ScrollControllerState",
                        "updateColumnContentHeight(${columnContentHeightPx.value}->$heightPx)"
                    )
                    columnContentHeightPx.value = heightPx
                }
            },
            clickArrowUp = {
                Log.d("ScrollControllerState", "clickArrowUp")
                scope.launch {
                    scrollState.animateScrollBy(-columnVisibleHeightPx.value.toFloat())
                }
            },
            clickArrowDown = {
                Log.d("ScrollControllerState", "clickArrowDown")
                scope.launch {
                    scrollState.animateScrollBy(columnVisibleHeightPx.value.toFloat())
                }
            },
            clickTrack = { yPx ->
                Log.d("ScrollControllerState", "clickTrack($yPx)")
                scope.launch {
                    val validRangeTop = thumbHeightPx.div(2)
                    val validRangeBottom = trackHeightPx.value - thumbHeightPx.div(2)

                    val nextValue = if (yPx <= validRangeTop) {
                        0
                    } else if (yPx >= validRangeBottom) {
                        scrollPositionMaxPx
                    } else {
                        val offset = yPx - validRangeTop
                        val offsetMax = validRangeBottom - validRangeTop
                        val offsetRatio = offset.toFloat() / offsetMax.toFloat()
                        scrollPositionMaxPx * offsetRatio
                    }
                    scrollState.scrollTo(nextValue.toInt())
                }
            },
            dragThumb = { dyPx ->
                Log.d("ScrollControllerState", "dragThumb($dyPx)")
                scope.launch {
                    scrollState.scrollBy(dyPx.toFloat())
                }
            }
        )
    }
}

private data class ScrollControllerState(
    val canClickArrowUp: Boolean,
    val canClickArrowDown: Boolean,
    val thumbTopPositionDp: Dp,
    val thumbHeightDp: Dp,
    val updateTrackHeight: (px: Int) -> Unit,
    val updateColumnVisibleHeight: (px: Int) -> Unit,
    val updateColumnContentHeight: (px: Int) -> Unit,
    val clickArrowUp: () -> Unit,
    val clickArrowDown: () -> Unit,
    val clickTrack: (px: Int) -> Unit,
    val dragThumb: (px: Int) -> Unit,
)

@Composable
@Preview(showBackground = true)
private fun AndroidViewLikeListViewPreview() {
    Scaffold { innerPadding ->
        AndroidViewLikeListView(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Text("list item1")
            Text("list item2")
        }
    }
}
