package com.partycalc.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.partycalc.PartyParticipantsRecyclerViewAdapter;
import com.partycalc.R;
import com.partycalc.database.Participant;
import com.partycalc.database.Party;
import com.partycalc.viewmodels.ActivePartyViewModel;
import com.partycalc.viewmodels.ActivePartyViewModelFactory;

import java.util.ArrayList;
import java.util.List;

public class ActivePartyActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private static final String TAG = "ActivePartyActivity";

    private ActivePartyViewModel activePartyViewModel;

    private RecyclerView mRecyclerView;
    private PartyParticipantsRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_party);

        Toolbar myToolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.party_participants_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PartyParticipantsRecyclerViewAdapter(new ArrayList<Participant>(), this, this);
        mRecyclerView.setAdapter(adapter);

        Party selectedParty = getIntent().getParcelableExtra("selected_party");

        ActivePartyViewModelFactory factory = new ActivePartyViewModelFactory(getApplication(), selectedParty);
        activePartyViewModel = ViewModelProviders.of(this, factory).get(ActivePartyViewModel.class);

        activePartyViewModel.getAllParticipants().observe(this, new Observer<List<Participant>>() {
            @Override
            public void onChanged(@Nullable final List<Participant> p) {
                Log.e(TAG, "AllParticipants was changed " + p.size());
                adapter.addItems(p);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.active_party_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_calculate:
                Toast.makeText(this, "Calculate item selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(this, "DEFAULT", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }

    public void addParticipantButtonClick(View view) {
        Intent intent = new Intent(this, AddParticipantToPartyActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2 && resultCode != 0) {
            Participant added_participant = data.getParcelableExtra("added_participant");
            activePartyViewModel.addParticipantToParty(added_participant);
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onLongClick(View view) {
        return false;
    }
}
