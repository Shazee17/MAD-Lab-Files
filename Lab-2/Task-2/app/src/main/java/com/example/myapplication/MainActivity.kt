package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
                    LayoutPractice()
                }
            }
        }
    }
}

@Composable
fun LayoutPractice(){
    var text by remember {
        mutableStateOf("Name")
    }
    var text2 by remember {
        mutableStateOf("Phone Number")
    }


    Row (verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)

    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = null,
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp)

            )
        )

        Column(modifier = Modifier.padding(10.dp)){
            TextField(value = text, onValueChange = {text = it}, modifier = Modifier.padding(bottom = 10.dp))
            TextField(value = text2, onValueChange = {text2 = it}, modifier = Modifier.padding(bottom = 10.dp))
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Preview() {
    MyApplicationTheme {
        LayoutPractice()
    }
}