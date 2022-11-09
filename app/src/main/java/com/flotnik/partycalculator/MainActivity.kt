package com.flotnik.partycalculator

import android.app.Application
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.flotnik.partycalculator.db.entities.Party
import com.flotnik.partycalculator.db.views.MainScreen
import com.flotnik.partycalculator.db.views.PartyScreen
import com.flotnik.partycalculator.ui.theme.PartyCalculatorTheme
import com.flotnik.partycalculator.viewmodels.MainViewModel
import com.flotnik.partycalculator.viewmodels.PartyScreenViewModel


class MainActivity : ComponentActivity() {

    @RequiresApi(33)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PartyCalculatorTheme {
                val owner = LocalViewModelStoreOwner.current

                owner?.let {
                    val viewModel: MainViewModel = viewModel(
                        it,
                        "MainViewModel",
                        MainViewModelFactory(LocalContext.current.applicationContext as Application)
                    )

                    ScreenSetup(viewModel)
                }
            }
        }
    }
}


@RequiresApi(33)
@Composable
fun ScreenSetup(viewModel: MainViewModel) {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "main") {
        composable("main") {
            MainScreen(
                viewModel = viewModel,
                Modifier,
                navController
            )
        }
        composable(
            route = "partyScreen/{party}",
            arguments = listOf(
                navArgument("party") {
                    type = Party.NavigationType
                }
            )) {
            PartyScreen(
                viewModel = PartyScreenViewModel(
                    currentParty = it.arguments?.getParcelable("party", Party::class.java)!!,
                    application = LocalContext.current.applicationContext as Application
                ),
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun ListTitleWithAddButton(title: String, onAddClick: () -> Unit, modifier: Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            title,
            modifier = modifier.padding(5.dp),
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Button(onClick = { onAddClick() }, modifier = Modifier) {
            Text(text = "Add")
        }
    }
}

@RequiresApi(33)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PartyCalculatorTheme {
        val owner = LocalViewModelStoreOwner.current

        owner?.let {
            val viewModel: MainViewModel = viewModel(
                it,
                "MainViewModel",
                MainViewModelFactory(LocalContext.current.applicationContext)
            )

            ScreenSetup(viewModel)
        }
    }
}

class MainViewModelFactory(private val application: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(application) as T
    }
}