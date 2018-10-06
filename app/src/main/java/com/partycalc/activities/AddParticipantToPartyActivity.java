package com.partycalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.partycalc.R;
import com.partycalc.database.Participant;

public class AddParticipantToPartyActivity extends AppCompatActivity {

    private EditText participantName;
    private EditText participantSize;
    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant_to_party);

        Toolbar myToolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);

        participantName = findViewById(R.id.participantName);
        participantSize = findViewById(R.id.participants_number);
        submit = findViewById(R.id.submitAddParticipantBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (participantName.getText() == null) {
                    Toast.makeText(AddParticipantToPartyActivity.this, R.string.party_name_is_incorrect, Toast.LENGTH_SHORT).show();
                } if(participantSize.getText() == null || participantSize.getText().toString().equalsIgnoreCase("0")){
                    Toast.makeText(AddParticipantToPartyActivity.this, R.string.participant_number_is_incorrect, Toast.LENGTH_SHORT).show();
                } else{
                    Participant added_participant = new Participant(participantName.getText().toString(),
                            Integer.parseInt(participantSize.getText().toString()));
                    Intent intent = new Intent();
                    intent.putExtra("added_participant", added_participant);
                    setResult(2, intent);
                    finish();
                }
            }
        });

    }
}
