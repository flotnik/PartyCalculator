package com.partycalc.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.partycalc.PartyParticipantsRecyclerViewAdapter
import com.partycalc.R
import com.partycalc.database.Contribution
import com.partycalc.database.Participant
import com.partycalc.database.Party
import com.partycalc.viewmodels.ActivePartyViewModel
import com.partycalc.viewmodels.ActivePartyViewModelFactory
import java.util.*

class ActivePartyActivity : AppCompatActivity(), OnLongClickListener, View.OnClickListener {

    companion object {
        private const val TAG = "ActivePartyActivity"
    }

    private lateinit var activePartyViewModel: ActivePartyViewModel
    private lateinit var mRecyclerView: androidx.recyclerview.widget.RecyclerView
    private var adapter: PartyParticipantsRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_active_party)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar2)
        setSupportActionBar(myToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        mRecyclerView = findViewById(R.id.party_participants_list)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PartyParticipantsRecyclerViewAdapter(ArrayList(), this, this)
        mRecyclerView.adapter = adapter
        val selectedParty: Party = intent.getParcelableExtra("selected_party")
        val factory = ActivePartyViewModelFactory(application, selectedParty)
        activePartyViewModel = ViewModelProvider(this, factory).get(ActivePartyViewModel::class.java)
        activePartyViewModel.allParticipants.observe(this, Observer { p ->
            Log.e(TAG, "AllParticipants was changed " + p!!.size)
            adapter!!.addItems(p)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.active_party_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_calculate -> {
                Toast.makeText(this, "Calculate item selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addParticipantButtonClick(view: View?) {
        val intent = Intent(this, AddParticipantToPartyActivity::class.java)
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode != 0) {
            val addedParticipant: Participant = data!!.getParcelableExtra("added_participant")
            val contributions = data.getParcelableArrayExtra("contributions") as Array<Contribution>
            activePartyViewModel.addParticipantToParty(addedParticipant)
        }
    }

    override fun onClick(view: View) {}

    override fun onLongClick(view: View): Boolean {
        return false
    }
}