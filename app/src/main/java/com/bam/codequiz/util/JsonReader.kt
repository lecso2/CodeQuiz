package com.bam.codequiz.util

import android.content.Context
import com.bam.codequiz.store.Puzzle
import com.bam.codequiz.store.PuzzleList
import com.google.gson.Gson
import java.io.InputStreamReader
import java.io.Reader
import java.nio.charset.StandardCharsets

object JsonReader {

    fun readLocalJson(context: Context): List<Puzzle> {
        val puzzles = mutableListOf<Puzzle>()

        context.assets.list("")?.filter { it.endsWith(".json") }?.forEach { fileName ->
            try {
                val input = context.assets.open(fileName)
                val reader: Reader = InputStreamReader(input, StandardCharsets.UTF_8)
                val puzzleList = Gson().fromJson(reader, PuzzleList::class.java)
                puzzles.addAll(puzzleList.puzzles)
            } catch (e: Exception) {
                println("JsonReader: Error in reading json: " + e.message)
            }
        }
        return puzzles
    }
}