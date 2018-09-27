package com.partycalc;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.partycalc.database.PartiesDatabase;
import com.partycalc.database.Party;
import com.partycalc.logic.AllParties;

public class MainActivity extends AppCompatActivity implements PartyFragment.OnListFragmentInteractionListener {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AllParties.db = Room.databaseBuilder(getApplicationContext(), PartiesDatabase.class, "database").build();
    }

    public void toastMe(View view){
        // Toast myToast = Toast.makeText(this, message, duration);
        Toast myToast = Toast.makeText(this, "Hello Toast!", Toast.LENGTH_SHORT);
        myToast.show();
    }

    @Override
    public void onListFragmentInteraction(Party item) {
        Intent intent = new Intent(this, ParticipantActivity.class);
        intent.putExtra(EXTRA_MESSAGE, item.getName());
        startActivity(intent);
    }
}
