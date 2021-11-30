package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme { Text("Hi there!", Modifier.firstBaselineToTop(24.dp)) }
        }
    }
}

fun Modifier.firstBaselineToTop(firstBaselineToTop: Dp) = layout { measurable, constraints ->
    //measurable은 Text를 나타냄
    //constraints는 element(Text)가 가질 수 있는  width, height에 대한 min/max 범위

    // Composable 측정
    val placeable = measurable.measure(constraints)

    // 측정값(갤S20 기준 - placeable.width:178px | placeable.height:65px

    //Check the composable has a first baseline
    check(placeable[FirstBaseline] != AlignmentLine.Unspecified)
    val firstBaseline = placeable[FirstBaseline]

    // 측정값 - firstBaseline: 51px

    // Height of the composable with padding - first baseline

    val placeableY = firstBaselineToTop.roundToPx() - firstBaseline //72 - 51 = 21
    val height = placeable.height + placeableY //65 + 21 = 76

    // 측정값 - firstBaselineToTop.roundToPx(): 72px, height: 76px

    layout(placeable.width, height) {
        // Where the composable gets placed
        placeable.placeRelative(0, placeableY)
    }
}


@Preview
@Composable
fun TextWithPaddingToBaselinePreview() {
    MyApplicationTheme { Text("Hi there!", Modifier.firstBaselineToTop(24.dp)) }
}

@Preview
@Composable
fun TextWithNormalPaddingPreview() {
    MyApplicationTheme { Text("Hi there!", Modifier.padding(top = 24.dp)) }
}

