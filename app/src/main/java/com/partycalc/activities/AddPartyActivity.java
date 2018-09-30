package com.partycalc.activities;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.partycalc.PartyViewModel;
import com.partycalc.R;
import com.partycalc.database.Party;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddPartyActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener  {

    private DatePickerDialog datePickerDialog;
    private Date date;
    private Calendar calendar;

    private EditText partyName;
    private EditText partyDate;
    private Button submit;

    private PartyViewModel partyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_party);

        partyName = findViewById(R.id.partyName);
        partyDate = findViewById(R.id.partyDate);
        submit = findViewById(R.id.submit);

        calendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, AddPartyActivity.this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

        partyViewModel = ViewModelProviders.of(this).get(PartyViewModel.class);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (partyName.getText() == null || date == null)
                    Toast.makeText(AddPartyActivity.this, "Empty filed", Toast.LENGTH_SHORT).show();
                else {
                    partyViewModel.addParty(new Party(partyName.getText().toString(),date));
                    finish();
                }
            }
        });

        findViewById(R.id.partyDate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        date = calendar.getTime();
        partyDate.setText(new SimpleDateFormat("yyyy.MM.dd").format(date));
    }
}
