package com.bam.codequiz

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bam.codequiz.fragments.MenuFragment
import com.bam.codequiz.fragments.PuzzleFragment
import com.bam.codequiz.fragments.ResultFragment
import com.bam.codequiz.store.ProgressStore

object Navigator {

    private lateinit var activity: MainActivity

    fun setup(activity: MainActivity) {
        this.activity = activity
    }

    private fun fragmentChange(fragment: Fragment, tag: String, addToBackStack: Boolean = true) {
        val ft: FragmentTransaction = activity.supportFragmentManager.beginTransaction()
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
        if (addToBackStack) ft.addToBackStack(null)
        ft.replace(R.id.fragment_placeholder, fragment, tag)
        ft.commit()
    }

    fun toMenu(addToBackStack: Boolean = true) {
        fragmentChange(MenuFragment(), "MenuFragment", addToBackStack)
    }

    fun toPuzzle(addToBackStack: Boolean = true, id: Int = 0) {
        fragmentChange(PuzzleFragment.newInstance(id), "PuzzleFragment", addToBackStack)
    }

    fun toResult(addToBackStack: Boolean = true) {
        fragmentChange(ResultFragment(), "ResultFragment", addToBackStack)
    }

    fun begin(category: String) {
        ProgressStore.setCategory(category)
        forward()
    }

    fun forward() {
        if (ProgressStore.hasNex()) {
            ProgressStore.next()
            toPuzzle(true)
        } else {
            toResult()
        }
    }

    fun back() {
        if (ProgressStore.hasPrevious()) {
            ProgressStore.previous()
            toPuzzle()
        } else {
            toMenu()
        }
    }

}