package com.partycalc.viewmodels

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import com.partycalc.database.Party

class ActivePartyViewModelFactory(private val application: Application, private val party: Party) : AndroidViewModelFactory(application) {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ActivePartyViewModel::class.java)) {
            return ActivePartyViewModel(application, party) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}