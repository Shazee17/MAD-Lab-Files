package com.example.labexam

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
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
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavigationController()
                }
            }
        }
    }
}

@Composable
fun NavigationController(

    context: Context = LocalContext.current

){
    // Navigation Controller
    val navController = rememberNavController()

    // Add transaction Arguments
    val transactionArguments = listOf(
        navArgument("current_amount"){
            type = NavType.FloatType
        },

        )

    val overviewArguments = listOf(
        navArgument("current_amount"){
            type = NavType.FloatType
        },
        navArgument("income"){
            type = NavType.FloatType
        },
        navArgument("expense"){
            type = NavType.FloatType
        }
    )
    NavHost(navController = navController, startDestination = "splash_screen"){
        composable(
            route = "splash_screen"
        ){
            SplashScreen("Splash Screen",navController=navController, context = context)
        }
        composable(
            route ="main_screen/{current_amount}",
            arguments = transactionArguments
        ){
            MainScreen("Main Screen",navController, context = context)
        }
        composable(
            route="overview_screen/{current_amount}/{income}/{expense}",
            arguments = overviewArguments
        ){
            OverviewScreen("Overview Screen", context = context)
        }
        composable(
            route ="add_transaction_screen",


            ){
            AddTransactionScreen("Add Transaction Screen",navController=navController, context = context)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen (
    name:String,
    navController: NavController,
    modifier: Modifier = Modifier,
    context: Context =  LocalContext.current
){

    val currentBalance = remember {
        mutableStateOf(0.0)

    }

    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "EconoTrecker", color = Color.White, fontSize = 20.sp) }
                , colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Home, null)
                    }
                }
            )
        },
        content = {
                paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.size(30.dp))
                Text(text = "Current Balance: $${currentBalance.value}", fontSize = 20.sp)
                Spacer(modifier = Modifier.size(40.dp))
                Button(onClick = { navController.navigate("add_transaction_screen") }) {
                    Text(text = "Add Income")
                }
                Spacer(modifier = Modifier.size(10.dp))
                Button(onClick = { navController.navigate("add_transaction_screen") }) {
                    Text(text = "Add Expense")
                }
            }
        }
    )

}


@Composable
fun SplashScreen (
    name:String,
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context =  LocalContext.current
){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center

    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "App Logo",
            modifier = Modifier
                .fillMaxSize()
                .size(width = 200.dp, height = 200.dp)
        )

        Text(text ="This is Splash Scrren")
    }


    LaunchedEffect(Unit) {
        // Delay for 2 seconds
        kotlinx.coroutines.delay(5000)
        navController.navigate("main_screen")

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OverviewScreen(
    name:String,
    modifier: Modifier = Modifier,
    context: Context =  LocalContext.current
){
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "")},
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Blue,
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { /TODO/ }) {
                        Icon(Icons.Default.Menu, null)

                    }
                },
                actions = {

                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Face, null)
                    }
                    IconButton(onClick = { /TODO/ }) {
                        Icon(Icons.Default.Home, null)
                    }
                }

            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /TODO/ }) {
                Text(text = "+", fontSize = 30.sp )

            }
        }
    ) {
            it -> Column (
        modifier = Modifier.padding(it)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .border(1.dp, Color.Blue, RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.padding(15.dp)

            ) {
                Text(text = "Income", fontSize = 20.sp)
                Text(text = "$1000", fontSize = 50.sp)
            }

            IconButton(onClick = {  }) {
                Icon(Icons.Default.KeyboardArrowUp, null, Modifier.size(50.dp))
            }

        }



        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .border(1.dp, Color.Blue, RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.padding(15.dp)

            ) {
                Text(text = "Expense", fontSize = 20.sp)
                Text(text = "-$1000", fontSize = 50.sp)
            }

            IconButton(onClick = {  }) {
                Icon(Icons.Default.KeyboardArrowUp, null, Modifier.size(50.dp))
            }

        }



        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(30.dp)
                .border(1.dp, Color.Blue, RoundedCornerShape(20.dp)),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column (
                modifier = Modifier.padding(15.dp)

            ) {
                Text(text = "Expense", fontSize = 20.sp)
                Text(text = "-$1000", fontSize = 50.sp)
            }

            IconButton(onClick = {  }) {
                Icon(Icons.Default.KeyboardArrowUp, null, Modifier.size(50.dp))
            }

        }




        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
        ) {
            Column (
  ) {
                Text(text = "Last Transactions", fontSize = 20.sp , fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "Description", fontSize = 20.sp)
                    Text(text = "Amount", fontSize = 20.sp)
                    Text(text = "Date", fontSize = 20.sp)
                    Text(text = " ", fontSize = 20.sp)

                }

                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "Inncome", fontSize = 20.sp)
                    Text(text = "$4000", fontSize = 20.sp)
                    Text(text = "01-march-1029", fontSize = 20.sp)
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Clear, null)
                    }

                }

                Spacer(modifier = Modifier.size(10.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                    ,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(text = "Description", fontSize = 20.sp)
                    Text(text = "Amount", fontSize = 20.sp)
                    Text(text = "Date", fontSize = 20.sp)
                    IconButton(onClick = {  }) {
                        Icon(Icons.Default.Clear, null)
                    }

                }
            }
        }

    }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTransactionScreen(
    name:String,
    modifier: Modifier = Modifier,
    navController: NavController,
    context: Context =  LocalContext.current
){

    var title = remember {
        mutableStateOf("")
    }

    var amount = remember {
        mutableStateOf("")
    }

    var transactionType = remember {
        mutableStateOf("")
    }


    Scaffold (
        topBar = {
            TopAppBar(
                title = { Text(text = "Back", color = Color.Blue, fontSize = 20.sp) }
                , colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color.Blue,
                    navigationIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, null)
                    }
                }
            )
        },
        content = {
                paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column (
                    modifier = Modifier
                        .padding(paddingValues),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    TextField(value = title.value,
                        onValueChange =  {title.value =it},
                        label = { Text(text = "Title")},

                        modifier = Modifier.size(300.dp, 60.dp),
                        textStyle = TextStyle(fontSize = 18.sp, color = Color.Blue),
                        shape = RoundedCornerShape(10.dp)
                    )

                    // Add Space between Text Fields
                    Spacer(modifier = Modifier.size(30.dp))


                    // amount
                    TextField(value = amount.value,
                        onValueChange = {amount.value =it},
                        label = {Text(text = "Amount")},

                        modifier = Modifier.size(300.dp, 60.dp),
                        textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )

                    // Add Space between Text Fields
                    Spacer(modifier = Modifier.size(30.dp))

                    // Transaction Type DropDown Menu
                    TextField(value = transactionType.value,
                        onValueChange = {transactionType.value =it},
                        label = {Text(text = "Transaction Type")},

                        modifier = Modifier.size(300.dp, 60.dp),
                        textStyle = TextStyle(fontSize = 18.sp, color = Color.White),
                        shape = RoundedCornerShape(10.dp),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                    )
                }

                Button(onClick = {
                    if (title.value.isEmpty() || title.value.isEmpty() || transactionType.value.isEmpty()){
                        Toast.makeText(context, "Enter correct data ", Toast.LENGTH_SHORT).show()
                    }else{
                        try {
//                            navController.navigate(route = "profile/${title.value}/${.value}")
                        }catch (e:Exception){
                            Toast.makeText(context, "Enter Correct values for Age", Toast.LENGTH_SHORT ).show()
                        }
                    }
                },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(30.dp)
                        .size(height = 60.dp, width = 300.dp)

                ) {
                    Text(
                        text = "Submit",
                        color = Color.Blue
                    )
                }


            }
        }
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabExamTheme {

    }
}
