package com.partycalc.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.partycalc.PartyRecyclerViewAdapter;
import com.partycalc.R;
import com.partycalc.database.Party;
import com.partycalc.viewmodels.PartyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    private PartyViewModel mModel;
    private RecyclerView mRecyclerView;
    private PartyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.parties_list);
        mRecyclerView.setHasFixedSize(true);
        adapter = new PartyRecyclerViewAdapter(new ArrayList<Party>(), this, this);
        mRecyclerView.setAdapter(adapter);

        mModel = ViewModelProviders.of(this).get(PartyViewModel.class);
        mModel.getParties().observe(this, new Observer<List<Party>>() {
            @Override
            public void onChanged(@Nullable final List<Party> p) {
                adapter.addItems(p);
            }
        });
    }

    public void addPartyButtonClick(View view){
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View view){
        Party item = (Party) view.getTag();
        Intent intent = new Intent(this, ActivePartyActivity.class);
        intent.putExtra("party_id", item.getId());
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_party_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Party borrowModel = (Party) view.getTag();
                mModel.deleteParty(borrowModel);
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
        return false;
    }
}
