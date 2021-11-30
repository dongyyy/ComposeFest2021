package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme { //theme 명은 프로젝트명 + "theme" 으로 자동설정된다.
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    PhotographerCard()
                }
            }
        }
    }
}


@Composable
fun PhotographerCard(modifier: Modifier = Modifier) {
    //첫번째 상황
//    Row {
//        Surface(
//            modifier = Modifier.size(50.dp),
//            shape = CircleShape,
//            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f) //alpha를 이렇게 먹이는구나???
//        ) {
//            //Image goes here
//        }
//        Column {
//            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
//            // LocalContentAlpha is defining opacity level of its children
//            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
//                Text("3 minutes ago", style = MaterialTheme.typography.body2)
//            }
//        }
    //두번째 상황 - 패딩주고 클릭할 수 있게 했다. 이를 위해 함수 파라미터로 그냥 Modifier를 받았다.
    Row(
        modifier
            .padding(8.dp)
            .clip(RoundedCornerShape(4.dp)) //새로운 모디파이어 출현
            .background(MaterialTheme.colors.surface)
            .clickable(onClick = { /* Ignoring onClick */ })
            .padding(16.dp) //padding과 clickable의 순서에 따라 클릭되는 영영이 달라짐
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            //Image goes here
        }
        Column(modifier = modifier
            .padding(start = 8.dp)
            .align(Alignment.CenterVertically)) {
            Text("Alfred Sisley", fontWeight = FontWeight.Bold)
            // LocalContentAlpha is defining opacity level of its children
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text("3 minutes ago", style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Preview
@Composable
fun PhotographerCardPreview() {
    MyApplicationTheme {
        PhotographerCard()
    }
}
