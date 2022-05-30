package com.bam.codequiz

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bam.codequiz.store.PuzzleStore
import com.bam.codequiz.util.JsonReader
import com.bam.codequiz.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getLocalData(this)

        //setup Navigator
        Navigator.setup(this)
        Navigator.toMenu()
        //Navigator.toPuzzle(false, 0)
    }

    private fun getLocalData(context: Context) {
        try {
            val puzzles = JsonReader.readLocalJson(context)
            PuzzleStore.addPuzzles(puzzles)
        } catch (e: Exception) {
            println("Error in local data: ${e.message}")
        }
    }

}