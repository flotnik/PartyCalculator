package com.partycalc;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.partycalc.database.PartiesDatabase;
import com.partycalc.database.Party;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    private PartyViewModel mModel;
    private RecyclerView mRecyclerView;
    private PartyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PartiesDatabase.getInstance(this).partyDAO().insert(new Party("trololo"));

        mRecyclerView = findViewById(R.id.parties_list);
        mRecyclerView.setHasFixedSize(true);
        adapter = new PartyRecyclerViewAdapter(new ArrayList<Party>());
        mRecyclerView.setAdapter(adapter);

        mModel = ViewModelProviders.of(this).get(PartyViewModel.class);
        mModel.getAllParties().observe(this, new Observer<List<Party>>() {
            @Override
            public void onChanged(@Nullable final List<Party> p) {
                adapter.updateList(p);
            }
        });
    }

    public void toastMe(View view){
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT);
        myToast.show();
    }

    public void onListFragmentInteraction(Party item) {
        Intent intent = new Intent(this, ParticipantActivity.class);
        intent.putExtra(EXTRA_MESSAGE, item.getName());
        startActivity(intent);
    }
}
