package com.flotnik.partycalculator.db.views

import android.app.Application
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flotnik.partycalculator.ListTitleWithAddButton
import com.flotnik.partycalculator.db.entities.Party
import com.flotnik.partycalculator.ui.theme.PartyCalculatorTheme
import com.flotnik.partycalculator.viewmodels.PartyScreenViewModel


@Composable
fun PartyScreen(viewModel: PartyScreenViewModel, onBackClick: () -> Unit) {
    val openAddNewDialog = remember { mutableStateOf(false) }
    val openRemoveItemDialog = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(
                        onClick = {
                            onBackClick()
                        }
                    ) {
                        Icon(Icons.Filled.ArrowBack, "Back")
                    }
                    Text(
                        text = "Party Details",
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f),
                        fontSize = 30.sp
                    )
                }
            }
        },
        content = { padding ->
            if (openRemoveItemDialog.value) {
                RemoveItemDialog(
                    viewModel = viewModel,
                    openDialog = openRemoveItemDialog,
                    title = "Confirm removing participant from party"
                )
            } else if (openAddNewDialog.value) {
                AddParticipantDialog(openDialog = openAddNewDialog, viewModel = viewModel)
            } else {
                Column(
                    modifier = Modifier
                        .padding(padding)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    OutlinedTextField(
                        value = viewModel!!.currentParty.name,
                        onValueChange = {},
                        label = { Text("Name") }
                    )
                    OutlinedTextField(
                        value = viewModel.currentParty.place,
                        onValueChange = {},
                        label = { Text("Place") }
                    )
                    ListTitleWithAddButton(
                        title = "Participants",
                        onAddClick = { openAddNewDialog.value = true },
                        modifier = Modifier
                    )
                    val values =
                        viewModel.currentPartyParticipants.observeAsState(initial = emptyList())

                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {

                        if (values.value.isEmpty()) {
                            item {
                                Text(
                                    fontSize = 10.sp,
                                    text = "No participants present, please add new"
                                )
                            }
                        } else {
                            for (v in values.value) {
                                item {
                                    Card(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                                            .clickable {

                                            },
                                        elevation = 10.dp,
                                        shape = RoundedCornerShape(5.dp)
                                    ) {
                                        Row(
                                            horizontalArrangement = Arrangement.Center,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(fontSize = 20.sp, text = v.name)
                                            IconButton(
                                                modifier = Modifier.padding(10.dp),
                                                onClick = {
                                                    viewModel.participantToRemoveFromParty = v
                                                    openRemoveItemDialog.value = true
                                                }
                                            )
                                            {
                                                Icon(
                                                    Icons.Filled.Delete,
                                                    "Delete participant from party"
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PartyCalculatorTheme {
        PartyScreen(
            viewModel = PartyScreenViewModel(LocalContext.current.applicationContext as Application,
            currentParty = Party(0, "fsdsdf", "frgrwwrghhhb"))) {
        }
    }
}