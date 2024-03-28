package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.time.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // Create a navController
                val navController = rememberNavController()

                // Display Greeting composable which includes NavHost
                Greeting(navController)
            }
        }
    }
}

@Composable
fun Greeting(navController: NavHostController, modifier: Modifier = Modifier) {

        NavHost(navController = navController, startDestination = "screen0") {
            composable("screen0") {
                Screen0(navController = navController)
            }
            composable("screen1") {
                Screen1(navController = navController)
            }
            composable("screen2") {
                Screen2(navController = navController)
            }
            composable("screen3") {
                Screen3(navController = navController)
            }
            composable("screen4") {
                Screen4(navController = navController)
            }
            composable("screen5") {
                Screen5(navController = navController)
            }
            composable("screen6") {
                Screen6(navController = navController)
            }
        }
    }


@Composable
fun Screen0(navController: NavHostController){
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(3000L) // Wait for 3 seconds

        // Navigate to Screen1 after 3 seconds
        navController.navigate("screen1")
    }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Image(
            painter = painterResource(id = R.drawable.avengers_logo),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )
    }
}


@Composable
fun Screen1(navController: NavHostController) {
    ScreenContent(navController, "Iron Man", R.drawable.iron_man)
}

@Composable
fun Screen2(navController: NavHostController) {
    ScreenContent(navController, "Captain America", R.drawable.captain_america)
}

@Composable
fun Screen3(navController: NavHostController) {
    ScreenContent(navController, "Thor", R.drawable.thor)
}

@Composable
fun Screen4(navController: NavHostController) {
    ScreenContent(navController, "Hulk", R.drawable.hulk)
}

@Composable
fun Screen5(navController: NavHostController) {
    ScreenContent(navController, "Black Widow", R.drawable.black_widow)
}

@Composable
fun Screen6(navController: NavHostController) {
    ScreenContent(navController, "Hawkeye", R.drawable.hawkeye)
}

@Composable
fun ScreenContent(navController: NavHostController, name: String, imageResource: Int) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .clip(CircleShape)
        )

        Text(text = name, fontSize = 30.sp)

        Button(onClick = {
            navController.navigate("screen${name.length % 6 + 1}")
        }) {
            Text(text = "Next")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting(rememberNavController())
    }
}
