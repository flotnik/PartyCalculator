package com.partycalc.activities;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.partycalc.PartyRecyclerViewAdapter;
import com.partycalc.R;
import com.partycalc.database.Party;
import com.partycalc.viewmodels.PartyViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener {

    private static final String TAG = "MainActivity";

    private PartyViewModel partyViewModel;
    private RecyclerView mRecyclerView;
    private PartyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        mRecyclerView = findViewById(R.id.parties_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new PartyRecyclerViewAdapter(new ArrayList<Party>(), this, this);
        mRecyclerView.setAdapter(adapter);

        partyViewModel = ViewModelProviders.of(this).get(PartyViewModel.class);
        //Log.e(TAG, "partyViewModel = " + partyViewModel.toString());

        partyViewModel.getParties().observe(this, new Observer<List<Party>>() {
            @Override
            public void onChanged(@Nullable final List<Party> p) {
                adapter.addItems(p);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_participants:
                Toast.makeText(MainActivity.this, "Participants item selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                Toast.makeText(MainActivity.this, "DEFAULT", Toast.LENGTH_SHORT).show();
                return super.onOptionsItemSelected(item);
        }
    }

    public void addPartyButtonClick(View view){
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode != 0){
            Party added_party = data.getParcelableExtra("added_party");
            partyViewModel.addParty(added_party);
        }
    }

    @Override
    public void onClick(View view){
        Party item = (Party) view.getTag();
        Intent intent = new Intent(this, ActivePartyActivity.class);
        intent.putExtra("selected_party", item);
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(final View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_party_message);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Party borrowModel = (Party) view.getTag();
                partyViewModel.deleteParty(borrowModel);
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
