package com.nasar.iqbal.basiccodelabs

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import com.nasar.iqbal.basiccodelabs.ui.theme.BasicCodeLabsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicCodeLabsTheme {
                // A surface container using the 'background' color from the theme
                mainEnteracne()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    val expanded = rememberSaveable {
        mutableStateOf(false)
    }
    val extraPadding by animateDpAsState(targetValue = if (expanded.value) 48.dp else 0.dp,
    animationSpec = spring(
        dampingRatio = Spring.DampingRatioMediumBouncy,
        stiffness = Spring.StiffnessLow
    ))

    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ){
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))) {
                Text(text = "Hello,")
                Text(text = "$name!",
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ))
                if (expanded.value) {
                    Text(
                        text = ("Composem ipsum color sit lazy, " +
                                "padding theme elit, sed do bouncy. ").repeat(4),
                    )
                }

            }
            IconButton(onClick = { expanded.value = expanded.value.not() }) {
                Icon(imageVector = if (expanded.value) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded.value) "Show less" else "Show more")
            }
        }


    }
}
@Preview(showBackground = true,
widthDp = 320,
uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320)
@Composable
fun DefaultPreview() {
    BasicCodeLabsTheme {
        MyApp()
    }
}

@Composable
fun mainEnteracne(){
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding){
        OnBoardingScreen {
            shouldShowOnboarding = false
        }
    }else{
        MyApp()
    }

}

@Composable
private fun MyApp(names: List<String> = List(1000) { "$it" }){
    Surface(color = MaterialTheme.colors.background) {
       LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
           items(items = names) { name ->
               Greeting(name = name)
           }
       }
    }
}

@Composable
fun OnBoardingScreen(onContinueClicked: () -> Unit){


    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Welcome to the Basics Codelab!")
            Button(onClick = onContinueClicked,
            modifier = Modifier.padding(vertical = 24.dp)) {
                Text(text = "Continue")
            }

        }
        
    }

}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    BasicCodeLabsTheme {
        OnBoardingScreen(onContinueClicked = {

        })
    }
}
