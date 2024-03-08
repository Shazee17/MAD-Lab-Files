package com.example.myapplication

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.core.content.edit
import com.example.myapplication.ui.theme.MyApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE )
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

class PreferencesManager(context: Context){
    //Step 1 - Create sharedPref Object
    val prefs = context.getSharedPreferences("myPrefs", Context.MODE_PRIVATE)

    //Step 2 - For Writing Purpose
    fun writeString(key: String, value: String){
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.commit()
    }

    //Step 3 - For Reading
    fun readString(key: String, defValue: String): String{
        return prefs.getString(key, defValue) ?: defValue
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier, context: Context = LocalContext.current) {
    val prefsMgr = PreferencesManager(context)

    var username by remember{
        mutableStateOf("")
    }

    val usernameValue = prefsMgr.readString("user", "Guest")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(value = username, onValueChange = {
            username = it
        })
        Button(onClick = {
            prefsMgr.writeString("user", username)
        }) {
            Text(text = "Save")
        }
        Text(usernameValue)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        Greeting("Android")
    }
}