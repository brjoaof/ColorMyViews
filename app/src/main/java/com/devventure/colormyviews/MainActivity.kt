package com.devventure.colormyviews

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.devventure.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var pencil: Int = R.color.gray

    private val boxes = arrayOf(
        R.id.box_one_text, R.id.box_two_text, R.id.box_three_text,
        R.id.box_four_text, R.id.box_five_text
    )

    private val sharedPreferences : SharedPreferences
        get(){
            return this.getSharedPreferences("colors", Context.MODE_PRIVATE)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadColors()

    }

    fun onButtonClick(view: View) {
        pencil = when(view.id) {
            R.id.btn_red -> R.color.red
            R.id.btn_green -> R.color.green
            R.id.btn_yellow -> R.color.yellow
            else -> R.color.gray
        }
    }

    fun onBoxClick(view: View) {
        view.setBackgroundResource(pencil)

        val editor = sharedPreferences.edit()
        editor.putInt("${view.id}", pencil)
        editor.commit()
    }

    private fun loadColors() {
        for (box in boxes) {
            findViewById<View>(box).setBackgroundResource(
                sharedPreferences.getInt("$box", R.color.gray)
            )
        }
    }
}