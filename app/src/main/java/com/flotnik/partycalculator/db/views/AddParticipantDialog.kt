package com.flotnik.partycalculator.db.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import com.flotnik.partycalculator.db.entities.Participant
import com.flotnik.partycalculator.viewmodels.PartyScreenViewModel

@Composable
fun AddParticipantDialog(openDialog: MutableState<Boolean>, viewModel: PartyScreenViewModel? = null) {
    val name = remember { mutableStateOf(TextFieldValue()) }

    AlertDialog(
        title = { Text("Add Participant", textAlign = TextAlign.Center) },
        text = {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        label = { Text(text = "Name") },
                        value = name.value,
                        onValueChange = {
                            name.value = it
                        },
                        singleLine = true
                    )
                }
            }
        },
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            Button(onClick = {
                viewModel?.let {
                    val participantId = it.addParticipant(
                        Participant(
                            0,
                            name = name.value.text
                        )
                    )
                    viewModel.addParticipantToParty(participantId)
                }

                openDialog.value = false
            }) { Text("Confirm") }
        },
        dismissButton = {
            Button(onClick = {
                openDialog.value = false
            }) { Text("Cancel") }
        }
    )
}