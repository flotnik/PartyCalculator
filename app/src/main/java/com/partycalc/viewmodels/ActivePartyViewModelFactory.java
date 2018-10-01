package com.partycalc.viewmodels;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.partycalc.database.Party;

public class ActivePartyViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    private final Party party;
    private final Application application;

    public ActivePartyViewModelFactory(Application application, Party party) {
        super(application);
        this.party = party;
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ActivePartyViewModel.class)) {
            return (T) new ActivePartyViewModel(application, party);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
