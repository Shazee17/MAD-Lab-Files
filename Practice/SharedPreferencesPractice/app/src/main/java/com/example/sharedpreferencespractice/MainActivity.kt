package com.example.sharedpreferencespractice

import android.content.Context
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.sharedpreferencespractice.ui.theme.SharedPreferencesPracticeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
            SharedPreferencesPracticeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

class preferencesMananger(context: Context){
    val prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

    fun saveString(key: String, value: String){
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getString(key: String, defValue: String): String{
        return prefs.getString(key,defValue) ?: defValue
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val prefsmgr = preferencesMananger(context)
    var name by remember {
       mutableStateOf("")
    }

    var nameValue = prefsmgr.getString("name", "User")

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Welcome back, $nameValue")

        TextField(value = name, onValueChange = {
            name = it
        }, label = { Text(text = "Name")})

        Button(onClick = { prefsmgr.saveString("name", name) }) {
            Text(text = "Save")
        }

    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SharedPreferencesPracticeTheme {
        Greeting()
    }
}