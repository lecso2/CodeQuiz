package com.bam.codequiz.widgets

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bam.codequiz.Navigator
import com.bam.codequiz.R

class Toolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    override fun onFinishInflate() {
        super.onFinishInflate()
        init()
    }

    private fun init() {
        setupLogoIcon()
        setupForwardIcon()
        setupBackIcon()
    }

    private fun setupLogoIcon() {
        val logoIcon = findViewById<ImageView>(R.id.logo_btn)
        logoIcon.setOnClickListener {
            Navigator.toMenu()
        }
    }

    private fun setupForwardIcon() {
        val icon = findViewById<TextView>(R.id.forward_btn)
        icon.setOnClickListener {
            Navigator.forward()
        }
    }

    private fun setupBackIcon() {
        val icon = findViewById<TextView>(R.id.back_btn)
        icon.setOnClickListener {
            Navigator.back()
        }
    }
}