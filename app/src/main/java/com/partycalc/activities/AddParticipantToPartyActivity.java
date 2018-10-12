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
import com.partycalc.database.Contribution;
import com.partycalc.database.Participant;

import java.util.ArrayList;
import java.util.List;

public class AddParticipantToPartyActivity extends AppCompatActivity {

    private EditText participantName;
    private EditText participantSize;

    private EditText contribName;
    private EditText contribAmount;

    private Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_participant_to_party);

        Toolbar myToolbar = findViewById(R.id.toolbar3);
        setSupportActionBar(myToolbar);

        participantName = findViewById(R.id.participantName);
        participantSize = findViewById(R.id.participants_number);

        contribName = findViewById(R.id.contribution_name);
        contribAmount = findViewById(R.id.contrib_ammount);

        submit = findViewById(R.id.submitAddParticipantBtn);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ("".equals(participantName.getText().toString())) {
                    Toast.makeText(AddParticipantToPartyActivity.this, "Must be bigger than 0", Toast.LENGTH_SHORT).show();
                }else if("".equals(participantSize.getText().toString()) || participantSize.getText().toString().equalsIgnoreCase("0")){
                    Toast.makeText(AddParticipantToPartyActivity.this, "Must be bigger than 0", Toast.LENGTH_SHORT).show();
                }else{
                    Participant added_participant = new Participant(participantName.getText().toString(), Integer.parseInt(participantSize.getText().toString()));
                    Contribution[] contributions = new Contribution[1];
                    contributions[0] = new Contribution(contribName.getText().toString(), Float.valueOf(contribAmount.getText().toString()));

                    Intent intent = new Intent();
                    intent.putExtra("added_participant", added_participant);
                    intent.putExtra("contributions", contributions);
                    setResult(2, intent);
                    finish();
                }
            }
        });

    }
}
