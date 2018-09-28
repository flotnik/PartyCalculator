package com.partycalc.activities;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.partycalc.PartyRecyclerViewAdapter;
import com.partycalc.PartyViewModel;
import com.partycalc.R;
import com.partycalc.database.Party;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, View.OnClickListener  {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

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

    public void toastMe(View view){
        Intent intent = new Intent(this, AddPartyActivity.class);
        startActivity(intent);
        /*Toast myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT);
        myToast.show();*/
    }

    public void onListFragmentInteraction(Party item) {
        Intent intent = new Intent(this, ParticipantActivity.class);
        intent.putExtra(EXTRA_MESSAGE, item.getName());
        startActivity(intent);
    }

    @Override
    public boolean onLongClick(View view) {
        Party borrowModel = (Party) view.getTag();
        mModel.deleteItem(borrowModel);
        return false;
    }

    @Override
    public void onClick(View view) {

    }
}
