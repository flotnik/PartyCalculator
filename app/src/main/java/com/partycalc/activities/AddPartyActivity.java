package com.partycalc.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.partycalc.PartyViewModel;
import com.partycalc.R;
import com.partycalc.database.Party;

public class AddPartyActivity extends AppCompatActivity {

    private EditText partyName;
    private Button submit;

    private PartyViewModel partyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);

        partyName = findViewById(R.id.partyName);
        submit = findViewById(R.id.submit);

        partyViewModel = ViewModelProviders.of(this).get(PartyViewModel.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (partyName.getText() == null)
                    Toast.makeText(AddPartyActivity.this, "Empty filed", Toast.LENGTH_SHORT).show();
                else {
                    partyViewModel.addParty(new Party(partyName.getText().toString()));
                    finish();
                }
            }
        });
    }
}
