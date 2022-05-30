package com.bam.codequiz.store

val EMPTY_PUZZLE = Puzzle(-1, "", "", listOf(), 0, "", listOf())

object PuzzleStore {

    private val puzzles = mutableListOf<Puzzle>()

//    init {
//        puzzles.add(
//            Puzzle(
//                1,
//                "title0",
//                listOf("answer0", "answer1", "answer2"),
//                1,
//                "explanation",
//                listOf("a")
//            )
//        )
//        puzzles.add(
//            Puzzle(
//                2,
//                "title1",
//                listOf("answer0", "answer1", "answer2"),
//                1,
//                "explanation",
//                listOf("b")
//            )
//        )
//        puzzles.add(
//            Puzzle(
//                3,
//                "title2",
//                listOf("answer0", "answer1", "answer2"),
//                1,
//                "explanation",
//                listOf("c")
//            )
//        )
//        puzzles.add(
//            Puzzle(
//                4,
//                "title3",
//                listOf("answer0", "answer1", "answer2"),
//                1,
//                "explanation",
//                listOf("a", "b", "c")
//            )
//        )
//    }

    fun getPuzzle(puzzleId: Int): Puzzle = puzzles.getOrNull(puzzleId) ?: EMPTY_PUZZLE

    fun getCategories(): List<String> {
        return puzzles.map { p -> p.tags.map { it } }.flatten().toSet().toList()
    }

    fun getCategory(category: String): List<Puzzle> {
        return puzzles.filter { it.tags.contains(category) }
    }

    fun addPuzzles(puzzles: List<Puzzle>) {
        PuzzleStore.puzzles.addAll(puzzles)
    }
}

data class Puzzle(
    val id: Int,
    val title: String,
    val code: String,
    val answers: List<String>,
    val correctAnswerNumber: Int,
    val explanation: String,
    val tags: List<String>
) : Comparable<Puzzle> {

    override fun compareTo(other: Puzzle): Int {
        val result = this.id.compareTo(other.id)
        if (result == 0) return this.title.compareTo(other.title)
        return result
    }

}

data class PuzzleList(val puzzles: List<Puzzle>)