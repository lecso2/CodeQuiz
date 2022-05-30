package com.bam.codequiz.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bam.codequiz.Navigator
import com.bam.codequiz.R
import com.bam.codequiz.databinding.FragmentMenuBinding
import com.bam.codequiz.store.PuzzleStore

class MenuFragment : Fragment() {

    lateinit var binding: FragmentMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(inflater, container, false)

        binding.categoryList.adapter = CategoryAdapter(PuzzleStore.getCategories()) {
            Navigator.begin(it)
        }

        return binding.root
    }

    class CategoryAdapter(
        private val items: List<String>,
        private val onItemClickListener: OnItemClickListener
    ) :
        RecyclerView.Adapter<ExerciseViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ExerciseViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_puzzle_category, parent, false)
        )

        override fun onBindViewHolder(holder: ExerciseViewHolder, position: Int) {
            holder.bind(items[position]) { onItemClickListener.onItemClick(it) }
        }

        override fun getItemCount(): Int {
            return items.count()
        }
    }

    fun interface OnItemClickListener {
        fun onItemClick(category: String)
    }

    class ExerciseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val titleText: TextView = view.findViewById(R.id.title)

        fun bind(category: String, clickListener: OnItemClickListener) {
            titleText.text = category
            itemView.setOnClickListener { clickListener.onItemClick(category) }
        }
    }
}