package com.dougpilcher.plantflashcards

import android.os.AsyncTask
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.dougpilcher.plantflashcards.dto.Plant
import com.dougpilcher.plantflashcards.service.PlantService

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun onButton1Clicked (view : View) {
        Snackbar.make(view, "You clicked button 1", Snackbar.LENGTH_SHORT).show()
    }

    fun onButton2Clicked (view : View) {
        Snackbar.make(view, "You clicked button 2", Snackbar.LENGTH_SHORT).show()

        // call Plant constructor to instantiate new Plant object
        var redbud = Plant(83, "Cersis", "canadensis", "", "Eastern Redbud")
        var pawpaw = Plant(100, "Asimina", "triloba", "Alleghany", "Alleghany Pawpaw", 10)
        var i = 1 + 1
    }

    fun onButton3Clicked (view : View) {
        Snackbar.make(view, "You clicked button 3", Snackbar.LENGTH_SHORT).show()

        // instantiate GetPlantsActivity inner class
        var getPlantsActivity = GetPlantsActivity()

        // execute() will create new thread and invoke doInBackground() in new thread
        getPlantsActivity.execute("string")
    }

    fun onButton4Clicked (view : View) {
        Snackbar.make(view, "You clicked button 4", Snackbar.LENGTH_SHORT).show()
    }

    inner class GetPlantsActivity : AsyncTask<String,Int,List<Plant>?>() {

        /**
         * Open a connection to a data feed to retrieve  data over a network
         * @param search the search text that will narrow down the results
         * @return return a collection of Plant objects that are parsed from JSON
         */
        override fun doInBackground(vararg search: String?): List<Plant>? {

            // collect first string passed to function
            var difficulty = search[0]

            // create instance of PlantService
            var plantService = PlantService()

            // invoke parsePlants function
            return plantService.parsePlantsFromJsonData(difficulty)
        }

        override fun onPostExecute(result: List<Plant>?) {
            super.onPostExecute(result)
        }

    }
}
