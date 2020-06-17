package com.partycalc.activities

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.partycalc.R
import com.partycalc.database.Party
import java.text.SimpleDateFormat
import java.util.*

class AddPartyActivity : AppCompatActivity(), OnDateSetListener {
    private var datePickerDialog: DatePickerDialog? = null
    private var date: Date? = null
    private lateinit var calendar: Calendar
    private var partyName: EditText? = null
    private var partyDate: EditText? = null
    private var submit: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_party)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar4)
        setSupportActionBar(myToolbar)
        partyName = findViewById(R.id.partyName)
        partyDate = findViewById(R.id.partyDate)
        submit = findViewById(R.id.submit)
        calendar = Calendar.getInstance()
        datePickerDialog = DatePickerDialog(this, this@AddPartyActivity, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        submit?.setOnClickListener(View.OnClickListener {
            if (partyName?.getText() == null) {
                Toast.makeText(this@AddPartyActivity, R.string.party_name_is_incorrect, Toast.LENGTH_SHORT).show()
            } else if (date == null) {
                Toast.makeText(this@AddPartyActivity, R.string.party_date_is_incorrect, Toast.LENGTH_SHORT).show()
            } else {
                val added_party = Party(partyName?.getText().toString(), date!!)
                val intent = Intent()
                intent.putExtra("added_party", added_party)
                Log.e(TAG, "TROLOLO")
                setResult(2, intent)
                finish()
            }
        })
        findViewById<View>(R.id.partyDate).setOnClickListener { datePickerDialog!!.show() }
    }

    override fun onDateSet(datePicker: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        calendar[Calendar.YEAR] = year
        calendar[Calendar.MONTH] = month
        calendar[Calendar.DAY_OF_MONTH] = dayOfMonth
        date = calendar.time
        partyDate!!.setText(SimpleDateFormat("yyyy.MM.dd").format(date))
    }

    companion object {
        private const val TAG = "AddPartyActivity"
    }
}