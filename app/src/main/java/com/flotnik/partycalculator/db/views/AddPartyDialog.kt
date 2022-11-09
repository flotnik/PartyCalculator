package com.flotnik.partycalculator.db.views

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.flotnik.partycalculator.db.entities.Party
import com.flotnik.partycalculator.viewmodels.MainViewModel
import java.util.*

@Composable
fun AddPartyDialog(openDialog: MutableState<Boolean>, viewModel: MainViewModel) {
    val nameState = remember { mutableStateOf(TextFieldValue()) }
    val placeState = remember { mutableStateOf(TextFieldValue()) }
    val mDate = remember { mutableStateOf("") }
    val mTime = remember {
        mutableStateOf("")
    }

    AlertDialog(
        title = { Text("Fill in Party info", textAlign = TextAlign.Center) },
        text = {
            Column(verticalArrangement = Arrangement.SpaceEvenly) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        label = { Text(text = "Name") },
                        value = nameState.value,
                        onValueChange = {
                            nameState.value = it
                        },
                        singleLine = true
                    )
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedTextField(
                        label = { Text(text = "Place") },
                        value = placeState.value,
                        onValueChange = {
                            placeState.value = it
                        },
                        singleLine = true
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val mContext = LocalContext.current   // Fetching the Local Context
                    OutlinedTextField(
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text(text = "Start Time", textAlign = TextAlign.Center) },
                        value = mTime.value,
                        onValueChange = {},
                        enabled = false,
                        singleLine = true,
                        readOnly = true,
                        modifier = Modifier
                            .width(120.dp)
                            .clickable(onClick = {
                                val mCalendar = Calendar.getInstance()
                                val mHour: Int = mCalendar.get(Calendar.HOUR)
                                val mMinute: Int = mCalendar.get(Calendar.MINUTE)
                                mCalendar.time = Date()

                                TimePickerDialog(
                                    mContext,
                                    { _: TimePicker, pmHour: Int, pmMinute: Int ->
                                        mTime.value = "$pmHour:$pmMinute"
                                    },
                                    mHour,
                                    mMinute,
                                    true
                                ).show()
                            })
                    )
                    OutlinedTextField(
                        textStyle = TextStyle(textAlign = TextAlign.Center),
                        label = { Text(text = "Start Date", textAlign = TextAlign.Center) },
                        value = mDate.value,
                        onValueChange = {},
                        enabled = false,
                        singleLine = true,
                        readOnly = true,
                        modifier = Modifier
                            .clickable(onClick = {
                                // Initializing a Calendar
                                val mCalendar = Calendar.getInstance()
                                // Fetching current year, month and day
                                val mYear: Int = mCalendar.get(Calendar.YEAR)
                                val mMonth: Int = mCalendar.get(Calendar.MONTH)
                                val mDay: Int = mCalendar.get(Calendar.DAY_OF_MONTH)
                                mCalendar.time = Date()

                                // Declaring DatePickerDialog and setting
                                // initial values as current values (present year, month and day)
                                DatePickerDialog(
                                    mContext,
                                    { _: DatePicker, pmYear: Int, pmMonth: Int, pmDayOfMonth: Int ->
                                        mDate.value = "$pmDayOfMonth/${pmMonth + 1}/$pmYear"
                                    }, mYear, mMonth, mDay
                                ).show()
                            })
                    )
                }
            }
        },
        onDismissRequest = { openDialog.value = false },
        confirmButton = {
            Button(onClick = {
                viewModel.insertParty(
                    Party(
                        0,
                        name = nameState.value.text,
                        place = placeState.value.text
                    )
                )
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