package com.partycalc.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.partycalc.R
import com.partycalc.database.Contribution
import com.partycalc.database.Participant

class AddParticipantToPartyActivity : AppCompatActivity() {
    private var participantName: EditText? = null
    private var participantSize: EditText? = null
    private var contribName: EditText? = null
    private var contribAmount: EditText? = null
    private lateinit var submit: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_participant_to_party)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar3)
        setSupportActionBar(myToolbar)
        participantName = findViewById(R.id.participantName)
        participantSize = findViewById(R.id.participants_number)
        contribName = findViewById(R.id.contribution_name)
        contribAmount = findViewById(R.id.contrib_ammount)
        submit = findViewById(R.id.submitAddParticipantBtn)
        submit.setOnClickListener(View.OnClickListener {
            if ("" == participantName?.getText().toString()) {
                Toast.makeText(this@AddParticipantToPartyActivity, "Must be bigger than 0", Toast.LENGTH_SHORT).show()
            } else if ("" == participantSize?.getText().toString() || participantSize?.getText().toString().equals("0", ignoreCase = true)) {
                Toast.makeText(this@AddParticipantToPartyActivity, "Must be bigger than 0", Toast.LENGTH_SHORT).show()
            } else {
                val added_participant = Participant(participantName?.getText().toString(), participantSize?.getText().toString().toInt())
                val contributions = arrayOfNulls<Contribution>(1)
                contributions[0] = Contribution(contribName?.getText().toString(), java.lang.Float.valueOf(contribAmount?.getText().toString()))
                val intent = Intent()
                intent.putExtra("added_participant", added_participant)
                intent.putExtra("contributions", contributions)
                setResult(2, intent)
                finish()
            }
        })
    }
}