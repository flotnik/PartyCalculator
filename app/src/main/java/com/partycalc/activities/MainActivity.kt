package com.partycalc.activities

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
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
import androidx.recyclerview.widget.RecyclerView
import com.partycalc.PartyRecyclerViewAdapter
import com.partycalc.R
import com.partycalc.database.Party
import com.partycalc.viewmodels.PartyViewModel
import java.util.*


class MainActivity : AppCompatActivity(), OnLongClickListener, View.OnClickListener {

    companion object {
        private const val TAG = "MainActivity"
    }

    private lateinit var partyViewModel: PartyViewModel
    private lateinit var mRecyclerView: RecyclerView
    private lateinit var adapter: PartyRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        mRecyclerView = findViewById(R.id.parties_list)
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = PartyRecyclerViewAdapter(ArrayList(), this, this)
        mRecyclerView.adapter = adapter
        partyViewModel = ViewModelProvider(this).get(PartyViewModel::class.java)
        //Log.e(TAG, "partyViewModel = " + partyViewModel.toString());
        partyViewModel.parties.observe(this, Observer { p -> adapter.addItems(p as List<Party>) })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_participants -> {
                Toast.makeText(this@MainActivity, "Participants item selected", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                Toast.makeText(this@MainActivity, "DEFAULT", Toast.LENGTH_SHORT).show()
                super.onOptionsItemSelected(item)
            }
        }
    }

    fun addPartyButtonClick(view: View?) {
        val intent = Intent(this, AddPartyActivity::class.java)
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2 && resultCode != 0) {
            val added_party: Party = data!!.getParcelableExtra("added_party")
            partyViewModel.addParty(added_party)
        }
    }

    override fun onClick(view: View) {
        val item = view.tag as Party
        val intent = Intent(this, ActivePartyActivity::class.java)
        intent.putExtra("selected_party", item)
        startActivity(intent)
    }

    override fun onLongClick(view: View): Boolean {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(R.string.delete_party_message)
        builder.setPositiveButton(R.string.ok) { dialog, id ->
            val borrowModel = view.tag as Party
            partyViewModel.deleteParty(borrowModel)
        }
        builder.setNegativeButton(R.string.cancel) { dialog, id -> dialog.cancel() }
        builder.create().show()
        return false
    }
}