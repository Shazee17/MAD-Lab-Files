package com.example.labexam

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.labexam.ui.theme.LabExamTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LabExamTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                        composable(
                            route = "screen3/{title}/{amount}/{type}/{reason}",
                            arguments = listOf(
                                navArgument("title") { type = NavType.StringType },
                                navArgument("amount") { type = NavType.StringType },
                                navArgument("type") { type = NavType.StringType },
                                navArgument("reason") { type = NavType.StringType }
                            )
                        ) { backStackEntry ->
                            val title = backStackEntry.arguments?.getString("title") ?: ""
                            val amount = backStackEntry.arguments?.getString("amount") ?: ""
                            val type = backStackEntry.arguments?.getString("type") ?: ""
                            val reason = backStackEntry.arguments?.getString("reason") ?: ""
                            Screen3(navController = navController, title, amount, type, reason)
                        }
                    }

                    NavHost(navController = navController, startDestination = "screen0"){

                    }
                }
            }
        }
    }
}

@Composable
fun Screen0(navController: NavHostController) {
    LaunchedEffect(Unit) {
        delay(3000L)

        navController.navigate("screen1")
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )
    }
}


@Composable
fun Screen1(navController: NavHostController) {
    val curBalance by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Current Balance: $curBalance")

        Button(onClick = {
            navController.navigate("screen2")
        }) {
            Text("Add Income")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Screen2(navController: NavHostController) {
    var title by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var type by remember { mutableStateOf("") }
    var reason by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Add Transaction") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        },

        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                //a label for the text field
                TextField(value = title,
                    onValueChange = { title = it },
                    label = { Text("Title")}

                )
                TextField(value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount")}
                    )

                Text(text = " Transaction Type")
                Row {
                    RadioButton(selected = type == "Income",
                        onClick = { type = "Income" },
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                    )
                    Text("Income")
                    RadioButton(selected = type == "Expense",
                        onClick = { type = "Expense" },
                        modifier = Modifier
                            .size(24.dp)
                            .padding(4.dp)
                    )
                    Text("Expense")
                }


                TextField(value = reason,
                    onValueChange = { reason = it },
                    label = { Text("Title")})

                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                    navController.navigate("screen3/$title/$amount/$type/$reason")
                }) {
                    Text("Add Transaction")
                }
            }
        }
    )


}

@Composable
fun Screen3(navController: NavHostController, title: String, amount: String, type: String, reason: String) {
    val totalIncome = 1000
    val totalExpenses = 500
    val netSavings = totalIncome - totalExpenses


    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Total Income")
        Card {
            Text("R$ $totalIncome", fontSize = 24.sp)
        }
        Text("Total Expenses")
        Card {
            Text("R$ $totalExpenses", fontSize = 24.sp)
        }


        Card {
            Text("Net Savings")
            Text("R$ $netSavings")
        }

        Text("Last Transaction")
        Row {
            Column {
                Text(text = "Description")
                Text(text = type)
            }
            Column {
                Text(text = "Amount")
                Text(text = amount)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabExamTheme {
        Screen2(rememberNavController())
    }
}
