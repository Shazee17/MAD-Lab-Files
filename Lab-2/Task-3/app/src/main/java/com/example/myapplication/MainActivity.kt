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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                    val names = listOf("Iron Man", "Captain America", "Thor", "Black Widow", "Hulk", "Hawkeye")
                    val ages = listOf("40", "100", "1500", "35", "50", "45")
                    val imageResources = listOf(
                        R.drawable.iron_man,
                        R.drawable.captain_america,
                        R.drawable.thor,
                        R.drawable.black_widow,
                        R.drawable.hulk,
                        R.drawable.hawkeye
                    )


                    MultipleRows(names, ages, imageResources)
                }
            }
        }
    }
}

@Composable
fun MultipleRows(names: List<String>, ages: List<String>, imageResources: List<Int>) {

    LazyColumn{
        items(names.size){ i ->
            ElevatedCard (modifier = Modifier
                .padding(10.dp),

                elevation = CardDefaults.cardElevation(defaultElevation = 16.dp)
            ) {
                Row (verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)

                ) {
                    val imageModifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(10.dp))

                    Image(painter = painterResource(id = imageResources[i]),
                        contentDescription = null,
                        modifier = imageModifier
                    )

                    Column(modifier = Modifier.padding(10.dp)){
                        Text(text = names[i], modifier = Modifier.padding(bottom = 10.dp), fontSize = 25.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Age: ${ages[i]}")

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    MyApplicationTheme {
        val names = listOf("Iron Man", "Captain America", "Thor", "Black Widow", "Hulk", "Hawkeye")
        val ages = listOf("40", "100", "1500", "35", "50", "45")
        val imageResources = listOf(
            R.drawable.iron_man,
            R.drawable.captain_america,
            R.drawable.thor,
            R.drawable.black_widow,
            R.drawable.hulk,
            R.drawable.hawkeye
        )


        MultipleRows(names, ages, imageResources)
    }
}