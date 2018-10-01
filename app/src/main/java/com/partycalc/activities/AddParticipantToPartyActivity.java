package com.partycalc.activities;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.partycalc.R;
import com.partycalc.database.Participant;
import com.partycalc.viewmodels.ActivePartyViewModel;

public class AddParticipantToPartyActivity extends AppCompatActivity {

    private EditText participantName;
    private Button submit;

    ActivePartyViewModel activePartyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant_to_party);

        participantName = findViewById(R.id.participantName);
        submit = findViewById(R.id.submitAddParticipantBtn);

        activePartyViewModel = ViewModelProviders.of(this).get(ActivePartyViewModel.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (participantName.getText() == null){
                    Toast.makeText(AddParticipantToPartyActivity.this, R.string.party_name_is_incorrect, Toast.LENGTH_SHORT).show();
                }else {
                    activePartyViewModel.addParticipantToParty(new Participant("", 1));
                    finish();
                }
            }
        });

    }
}
