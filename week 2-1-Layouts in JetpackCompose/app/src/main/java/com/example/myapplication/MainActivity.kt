package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
//                SimpleList()
//                LazyList()
                ScrollingList()
            }
        }
    }
}

//1. Column은 기본적으로 스크롤을 처리하지 않기 때문에 일부 항목은 화면 외부에 있기 때문에 표시되지 않습니다.
//@Composable
//fun SimpleList() {
//    Column {
//        repeat(100) {
//            Text("Item #$it")
//        }
//    }
//}

//2. Column 내에서 스크롤할 수 있도록 verticalScroll 수정자를 추가합니다.
//@Composable
//fun SimpleList() {
//    // We save the scrolling position with this state that can also
//    // be used to programmatically scroll the list
//    val scrollState = rememberScrollState()
//
//    Column(Modifier.verticalScroll(scrollState)) {
//        repeat(100) {
//            Text("Item #$it")
//        }
//    }
//}

//3. 오직 스크린에 보이는 항목만 랜더링하는 LazyColumn을 사용
//@Composable
//fun LazyList() {
//    // We save the scrolling position with this state that can also
//    // be used to programmatically scroll the list
//    val scrollState = rememberLazyListState()
//
//    LazyColumn(state = scrollState) {
//        items(100) {
//            Text("Item #$it")
//        }
//    }
//}

@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter( //coil
                data = "https://developer.android.com/images/brand/Android_Robot.png"
            ),
            contentDescription = "Android Logo",
            modifier = Modifier.size(50.dp)
        )
        Spacer(Modifier.width(10.dp))
        Text("Item #$index", style = MaterialTheme.typography.subtitle1)
    }
}

@Composable
fun ScrollingList() {
    val listSize = 100
    // We save the scrolling position with this state
    val scrollState = rememberLazyListState()
    // We save the coroutine scope where our animated scroll will be executed
    val coroutineScope = rememberCoroutineScope()

    Column {
        Row {
            Button(onClick = {
                coroutineScope.launch {
                    // 0 is the first item index
                    scrollState.animateScrollToItem(0)
                }
            }) {
                Text("Scroll to the top")
            }

            Button(onClick = {
                coroutineScope.launch {
                    // listSize - 1 is the last index of the list
                    scrollState.animateScrollToItem(listSize - 1)
                }
            }) {
                Text("Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(listSize) {
                ImageListItem(it)
            }
        }
    }
}

@Preview
@Composable
fun LayoutsCodelabPreview() {
    MyApplicationTheme {
//        SimpleList()
//        LazyList()
        ScrollingList()
    }
}
