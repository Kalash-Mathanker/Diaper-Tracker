package com.example.diapertracker

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.core.content.getSystemService

class MainActivity : AppCompatActivity() {

    private lateinit var dirtyDiaper : RadioButton
    private lateinit var wetDiaper : RadioButton
    private lateinit var dryDiaper : RadioButton
    private lateinit var time : EditText
    private lateinit var diaperChange : TextView
    private lateinit var diaperCounter : TextView

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addDiaper : Button = findViewById(R.id.main_activity_bt_add)
        val clearButton : Button = findViewById(R.id.main_activity_bt_clear)

        addDiaper.setOnClickListener { addNewDiaper() }
        clearButton.setOnClickListener { clear() }

        dirtyDiaper = findViewById(R.id.main_activity_rb_dirty)
        wetDiaper = findViewById(R.id.main_activity_rb_wet)
        dryDiaper = findViewById(R.id.main_activity_rb_dry)
        time = findViewById(R.id.main_activity_et_time)
        diaperChange = findViewById(R.id.main_activity_tv_diaper_change)
        diaperCounter = findViewById(R.id.main_activity_diaper_count)
    }

    private fun addNewDiaper()
    {
        var currentTime = "00:00"

        if(time.text.toString() != "")
        {
            currentTime = time.text.toString()
        }
        var newDiaper = ""

        // when can also be used
        newDiaper = if(dirtyDiaper.isChecked) {
            //Log.i("test", "Dirty Diaper is changed")
            "- A dirty diaper was changed at $currentTime"
        } else if(wetDiaper.isChecked) {
            //Log.i("test", "Wet Diaper is changed")
            "- A wet diaper was changed at $currentTime"
        } else {
            //Log.i("test", "Dry Diaper is changed")
            "- A dry diaper was changed at $currentTime"
        }

        counter++

        updateDiaperList(newDiaper)
    }

    private fun updateDiaperList(newDiaper : String)
    {
        val oldDiaper = diaperChange.text.toString()

        val updatedDiaper = "$oldDiaper \n $newDiaper"

        diaperChange.text = updatedDiaper

        diaperCounter.text = "$counter total diapers changed."

        time.setText("")

        hideKeyBoard()
    }

    private fun clear()
    {
        diaperChange.text = ""
        diaperCounter.text = ""
        counter = 0
    }

    private fun hideKeyBoard()
    {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(time.windowToken, 0)
    }

}