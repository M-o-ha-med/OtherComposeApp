package com.example.othercomposeapp

import android.os.Bundle
import android.view.Surface
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.othercomposeapp.ui.theme.OtherComposeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            OtherComposeAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    Surface(color= MaterialTheme.colorScheme.primary , modifier = modifier.padding(vertical  = 4.dp, horizontal = 8.dp)) {


        Row(modifier = Modifier.padding(24.dp)){
            Column(modifier = Modifier.weight(1f).padding(bottom = extraPadding)){
                Text(text = "Hello ")
                Text(text = name)
            }

            ElevatedButton(onClick = {expanded.value = !expanded.value}) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun Greetings(modifier: Modifier = Modifier , names : List<String> = List(1000) {"$it"}){
    LazyColumn (modifier = modifier.padding(vertical = 4.dp)){
        items(items  = names) {name ->
            Greeting(name  = name)
        }
    }
}

@Composable
fun OnboardingScreen(onContinueClicked: () -> Unit , modifier: Modifier = Modifier){
    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        Text("Welcome to the Basics Codelab!")
        Button(modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked) {
            Text("Continue")
        }
    }
}

@Composable
fun MyApp(modifier: Modifier = Modifier ){
    var shouldShowOnboarding by remember { mutableStateOf(true) }

    Surface(modifier){
        if (shouldShowOnboarding){
            OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
        }
        else{
            Greetings()
        }

    }
}



@Preview(showBackground = true,
    name = "Onboard_Preview",
    widthDp = 320,
    heightDp = 320
)
@Composable
fun OnboardPreview() {
    OtherComposeAppTheme {
       OnboardingScreen(onContinueClicked = {})
    }
}

@Preview(showBackground = true , name = "Greetings")
@Composable
fun GreetingPreview() {
    OtherComposeAppTheme {
        Greetings()
    }
}

@Preview(name = "App_Preview",
    widthDp = 320,
    heightDp = 320
)
@Composable
fun appPreview(){
    OtherComposeAppTheme {
        MyApp()
    }
}