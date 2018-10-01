package com.partycalc.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.partycalc.PartyParticipantsRecyclerViewAdapter;
import com.partycalc.R;
import com.partycalc.database.Participant;
import com.partycalc.database.Party;
import com.partycalc.viewmodels.ActivePartyViewModel;
import com.partycalc.viewmodels.ActivePartyViewModelFactory;
import com.partycalc.viewmodels.PartyViewModel;

import java.util.ArrayList;
import java.util.List;

public class ActivePartyActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private static final String TAG = "ActivePartyActivity";

    private ActivePartyViewModel activePartyViewModel;
    private PartyViewModel partyViewModel;

    private RecyclerView mRecyclerView;
    private PartyParticipantsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant);

        Intent intent = getIntent();// Get the Intent that started this activity and extract the string
        int party_id = intent.getIntExtra("party_id", -1);
        Log.i(TAG, "party_id=" + party_id);

        mRecyclerView = findViewById(R.id.party_participants_list);
        mRecyclerView.setHasFixedSize(true);
        adapter = new PartyParticipantsRecyclerViewAdapter(new ArrayList<Participant>(), this, this);
        mRecyclerView.setAdapter(adapter);

        partyViewModel = ViewModelProviders.of(this).get(PartyViewModel.class);
        Log.i(TAG, "partyViewModel size=" + partyViewModel.getParties().getValue().size());
        Party selectedParty;
        try {
            selectedParty = partyViewModel.getPartyById(party_id);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        Log.i(TAG, "selectedParty party_id=" + selectedParty.getName());
        ActivePartyViewModelFactory factory = new ActivePartyViewModelFactory(getApplication(), selectedParty);
        activePartyViewModel = ViewModelProviders.of(this, factory).get(ActivePartyViewModel.class);

        activePartyViewModel.getAllParticipants().observe(this, new Observer<List<Participant>>() {
            @Override
            public void onChanged(@Nullable final List<Participant> p) {
                adapter.addItems(p);
            }
        });
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
