package com.bam.codequiz.store

import android.util.Log

object ProgressStore {

    private var index = 0
    private var category = mutableListOf<Puzzle>()
    private var answers = mutableMapOf<Int, Boolean>()

    fun setCategory(categoryTag: String) {
        category.clear()
        category.addAll(PuzzleStore.getCategory(categoryTag))
        index = -1
    }

    fun setAnswer(id: Int, isCorrect: Boolean) {
        answers[id] = isCorrect
    }

    fun hasNex() = index < category.size - 1

    fun next() = index++

    fun hasPrevious() = index > 0

    fun previous() = index--

    fun getPuzzle(): Puzzle {
        return category.getOrElse(index) {
            Log.e(this.toString(), "No Puzzle! Index: $index")
            EMPTY_PUZZLE
        }
    }

    fun getCorrectAnswers(): String {
        return "${answers.filter { it.value }.keys.size} / ${category.size}"
    }

}