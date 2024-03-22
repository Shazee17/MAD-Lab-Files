package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    var count by rememberSaveable {
//                        mutableStateOf(0)
//                    }
//
//                    Counter(count = count, onCountChange = {
//                        newCount -> count = newCount
//                    })

                    STATT()
                }
            }
        }
    }
}


@Composable
fun Counter(count: Int, onCountChange: (Int) -> Unit){
    Button(onClick = {onCountChange(count + 1)}) {
        Text(text = "Plus $count")
    }
}

@Composable
fun STATT(){
    var value by remember {
        mutableStateOf(0)
    }


    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = "VALUE: $value")
        Button(onClick = {value++}) {
            Text(text = "Plus")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MyApplicationTheme {
        STATT()
    }
}