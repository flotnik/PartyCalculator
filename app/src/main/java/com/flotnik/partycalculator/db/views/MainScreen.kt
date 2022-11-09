package com.flotnik.partycalculator.db.views

import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.flotnik.partycalculator.ListTitleWithAddButton
import com.flotnik.partycalculator.viewmodels.MainViewModel
import com.google.gson.Gson

@Composable
fun MainScreen(
    viewModel: MainViewModel,
    modifier: Modifier,
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.fillMaxWidth()
                ) {
                    Text("Party Calculator", fontSize = 30.sp, textAlign = TextAlign.Center)
                }
            }
            )
        },
        drawerContent = {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
            ) {
                Text(
                    text = "drawerContent",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        },
        content = { padding ->
            val openDialog = remember { mutableStateOf(false) }

            if (!openDialog.value) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier.padding(padding)
                ) {
                    ListTitleWithAddButton(
                        title = "Parties List",
                        onAddClick = { openDialog.value = true },
                        modifier = modifier
                    )
                    PartiesList(
                        viewModel,
                        onItemClick = { partyJson -> navController.navigate("partyScreen/$partyJson") }
                    )
                }
            } else {
                AddPartyDialog(openDialog, viewModel)
            }
        }
    )
}

@Composable
fun PartiesList(viewModel: MainViewModel, onItemClick: (String) -> Unit) {
    val values = viewModel.allParties.observeAsState(initial = emptyList())

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {

        if (values.value.isEmpty()) {
            item {
                Text(fontSize = 20.sp, text = "No parties present, please add new")
            }
        } else {
            for (v in values.value) {
                item {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                            .clickable {
                                val partyJson = Uri.encode(Gson().toJson(v))
                                viewModel.currentParty = v
                                onItemClick(partyJson)
                            },
                        elevation = 10.dp,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center,
                                modifier = Modifier.weight(1f)
                            )
                            {
                                Text(fontSize = 20.sp, text = v.name)
                                Text(fontSize = 15.sp, text = v.place)
                            }
                            IconButton(
                                modifier = Modifier.padding(10.dp),
                                onClick = {
                                    viewModel.deleteParty(v)
                                }
                            )
                            {
                                Icon(Icons.Filled.Delete, "Delete Party")
                            }
                        }
                    }
                }
            }
        }
    }
}
