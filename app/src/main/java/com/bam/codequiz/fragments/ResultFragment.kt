package com.bam.codequiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bam.codequiz.Navigator
import com.bam.codequiz.store.ProgressStore
import com.bam.codequiz.databinding.FragmentResultBinding

class ResultFragment : Fragment() {

    lateinit var binding: FragmentResultBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResultBinding.inflate(inflater, container, false)
        binding.result.text = ProgressStore.getCorrectAnswers()
        binding.logoBtn.setOnClickListener {
            Navigator.toMenu()
        }

        return binding.root
    }

}