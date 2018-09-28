package com.partycalc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.partycalc.database.PartiesDatabase;
import com.partycalc.database.Party;

import java.util.List;

public class PartyViewModel extends AndroidViewModel {

    private MutableLiveData<List<Party>> mCurrentName;

    public PartyViewModel(@NonNull Application application) {
        super(application);
    }

    public MutableLiveData<List<Party>> getAllParties() {
        if (mCurrentName == null) {
            mCurrentName = new MutableLiveData<>();
            mCurrentName.postValue(PartiesDatabase.getInstance(getApplication().getBaseContext()).partyDAO().getAllParties().getValue());
        }
        return mCurrentName;
    }
}
