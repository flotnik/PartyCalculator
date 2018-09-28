package com.partycalc;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.partycalc.database.PartiesDatabase;
import com.partycalc.database.Party;

import java.util.List;

public class PartyViewModel extends AndroidViewModel {

    private final LiveData<List<Party>> partiesList;
    private PartiesDatabase appDatabase;

    public PartyViewModel(@NonNull Application application) {
        super(application);

        appDatabase = PartiesDatabase.getInstance(this.getApplication());
        partiesList = appDatabase.partyDAO().getAllParties();
    }

    public LiveData<List<Party>> getParties() {
        return partiesList;
    }

    public void deleteItem(Party borrowModel) {
        new deleteAsyncTask(appDatabase).execute(borrowModel);
    }

    public void addParty(Party party) {
        new addAsyncTask(appDatabase).execute(party);
    }



    private static class deleteAsyncTask extends AsyncTask<Party, Void, Void> {

        private PartiesDatabase db;

        deleteAsyncTask(PartiesDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Party... params) {
            db.partyDAO().delete(params);
            return null;
        }
    }

    private static class addAsyncTask extends AsyncTask<Party, Void, Void> {

        private PartiesDatabase db;

        addAsyncTask(PartiesDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final Party... params) {
            db.partyDAO().insert(params);
            return null;
        }
    }
}
