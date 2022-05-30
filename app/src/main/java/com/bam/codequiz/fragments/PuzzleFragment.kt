package com.bam.codequiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import com.bam.codequiz.R
import com.bam.codequiz.databinding.FragmentPuzzleBinding
import com.bam.codequiz.store.ProgressStore


//const val PUZZLE_ID_KEY = "PUZZLE_ID_KEY"

class PuzzleFragment : Fragment() {

    private lateinit var binding: FragmentPuzzleBinding

    var isExplanationOpen = false
//    private var puzzleId: Int = 0
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        puzzleId = arguments?.getInt(PUZZLE_ID_KEY) ?: 0
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPuzzleBinding.inflate(inflater, container, false)
        val puzzle = ProgressStore.getPuzzle()

        binding.title.text = puzzle.title
        binding.code.text = puzzle.code
//        binding.explanationTitle.setOnClickListener { toggleExplanation(puzzle.explanation) }
        binding.explanationBody.text = puzzle.explanation
        addButtons(puzzle.id, puzzle.answers, puzzle.correctAnswerNumber)

        return binding.root
    }

//    private fun toggleExplanation(explanation: String) {
//        isExplanationOpen = !isExplanationOpen
//        if (isExplanationOpen) {
//            binding.explanationLayout.visibility = View.VISIBLE
//        } else {
//            binding.explanationLayout.visibility = View.GONE
//        }
//    }

//    private fun toggle2() {
//        val animationDuration = 300
//        val layoutTransition = LayoutTransition()
//        layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
//        (layout.parent as ViewGroup).layoutTransition = layoutTransition
//        layout.layoutTransition = layoutTransition
//
//        val currentSet = ConstraintSet()
//        currentSet.clone(layout)
//        currentSet.clear(arrowId)
//
//        currentSet.setVisibility(
//            layoutIdOpen,
//            if(isOpen==true) View.Visible else View.GONE
//        )
//
//        TransitionManager.beginDelayedTransition(layout.parent as ViewGroup, getLayoutTransitionSet(animationDuration))
//        currentSet.applyTo(layout)
//    }
//
//    fun getLayoutTransitionSet(animationDuration:Int) : TransitionSet {
//        return TransitionSet().setOrderint(if(Build.VERSION.SDK_INT < Build.VERSION_CODES.O)){
//
//        }
//    }

    companion object {
        fun newInstance(puzzleId: Int): PuzzleFragment {
            return PuzzleFragment().apply {
//                arguments = Bundle().apply { putInt(PUZZLE_ID_KEY, puzzleId) }
            }
        }
    }

    private fun addButtons(puzzleId: Int, answers: List<String>, correctAnswerNumber: Int) {
        answers.forEachIndexed { index, s ->
            val rButton = RadioButton(requireContext())
            rButton.id = View.generateViewId()
            rButton.text = "$s"
            rButton.setTextColor(resources.getColor(R.color.secondary))
            val typeface = ResourcesCompat.getFont(requireContext(), R.font.poppins_medium)
            rButton.typeface= typeface
            rButton.setOnClickListener { onAnswerClick(puzzleId, correctAnswerNumber, index) }
            val params = RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(15, 15, 15, 15)
            rButton.layoutParams = params
            binding.answers.addView(rButton)
        }

        binding.answers
    }

    private fun onAnswerClick(puzzleId: Int, correctAnswerNumber: Int, index: Int) {
        if (index == correctAnswerNumber) {
            ProgressStore.setAnswer(puzzleId, true)
        } else {
            ProgressStore.setAnswer(puzzleId, false)
            binding.resultSuffix.visibility = View.VISIBLE
        }
        setButtonAnswer(correctAnswerNumber)
        binding.result.visibility = View.VISIBLE
        binding.explanationLayout.visibility = View.VISIBLE
    }

    private fun setButtonAnswer(correctAnswerNumber: Int) {
        binding.answers.forEachIndexed { index, view ->
            (view as RadioButton).isEnabled = false
            if (index == correctAnswerNumber) {
                view.setTextColor(resources.getColor(R.color.primary))
            }
        }
    }
}