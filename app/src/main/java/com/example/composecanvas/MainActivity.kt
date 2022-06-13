package com.example.composecanvas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composecanvas.ui.BallClicker
import com.example.composecanvas.ui.CountDownTimer
import com.example.composecanvas.ui.theme.ComposeCanvasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeCanvasTheme {
                //  MyCanvas()
                MainScreen()
            }
        }
    }


}

@Composable
fun MainScreen() {
    var points by remember {
        mutableStateOf(0)
    }
    var isTimerRunning by remember {
        mutableStateOf(false)
    }
    Column(modifier = Modifier.fillMaxSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Points:$points",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Button(onClick = {
                isTimerRunning = !isTimerRunning
                points = 0
            }) {

                Text(text = if (isTimerRunning) "Reset" else "Start")
            }
            CountDownTimer(isTimerRunning = isTimerRunning) {
                isTimerRunning = false
            }
        }
        BallClicker(enable = isTimerRunning) {
            points++
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeCanvasTheme {

    }
}