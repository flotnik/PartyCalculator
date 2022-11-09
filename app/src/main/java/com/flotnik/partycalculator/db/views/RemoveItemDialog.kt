package com.flotnik.partycalculator.db.views

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import com.flotnik.partycalculator.viewmodels.PartyScreenViewModel


@Composable
fun RemoveItemDialog(
    viewModel: PartyScreenViewModel,
    openDialog: MutableState<Boolean>,
    title: String
) {
    AlertDialog(
        title = { Text(title) },
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            Button(onClick = {
                viewModel.removeParticipantFromParty(viewModel.participantToRemoveFromParty!!.id)
                openDialog.value = false
            }) { Text("Confirm") }
        },
        dismissButton = {
            Button(onClick = { openDialog.value = false }) { Text("Cancel") }
        }
    )
}
