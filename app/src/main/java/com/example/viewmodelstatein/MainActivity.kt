package com.example.viewmodelstatein

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = NavKey.A.route) {
                composable(NavKey.A.route) {
                    AScreenRoute(
                        viewModel = viewModel(),
                        onNavToB = { navController.navigate(NavKey.B.route) })
                }
                composable(NavKey.B.route) {
                    BScreenRoute(
                        viewModel = viewModel(),
                    )
                }
            }
        }
    }
}

@Composable
fun AScreenRoute(
    viewModel: AViewModel,
    onNavToB: () -> Unit
) {
    AScreen(
        stateValue = viewModel.state.collectAsState().value,
        onNavToB = onNavToB,
        onIncrementState = viewModel::incrementState
    )
}

@Composable
fun AScreen(
    stateValue: Int,
    onNavToB: () -> Unit,
    onIncrementState: () -> Unit
) {
    Log.d("AScreen", "$stateValue")
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "State: $stateValue")
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onNavToB
            ) {
                Text(text = "Go to B")
            }

            Button(onClick = onIncrementState) {
                Text(text = "Increment State")
            }
        }
    }
}

@Composable
fun BScreenRoute(viewModel: BViewModel) {
    BScreen(stateValue = viewModel.state.collectAsState().value, onButtonClick = viewModel::incrementState)
}

@Composable
fun BScreen(
    stateValue: Int,
    onButtonClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "State: $stateValue")
            Spacer(modifier = Modifier.height(32.dp))
            Button(
                onClick = onButtonClick
            ) {
                Text(text = "Increment State")
            }
        }
    }
}

sealed class NavKey {
    abstract val route: String

    object A : NavKey() {
        override val route = "A"
    }

    object B : NavKey() {
        override val route = "B"
    }
}
