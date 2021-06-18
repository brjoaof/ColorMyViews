package com.devventure.colormyviews

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
//import com.devventure.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding

    private var boxOneColor = R.color.gray
    private var boxTwoColor = R.color.gray
    private var boxThreeColor = R.color.gray
    private var boxFourColor = R.color.gray
    private var boxFiveColor = R.color.gray

    private val sharedPreferences : SharedPreferences
        get(){
            return this.getSharedPreferences("colors", Context.MODE_PRIVATE)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
        setContentView(R.layout.activity_main)


        var pencil = R.color.gray
        val btnRed = findViewById<Button>(R.id.btn_red)
        val btnGreen = findViewById<Button>(R.id.btn_green)
        val btnYellow = findViewById<Button>(R.id.btn_yellow)

        val boxOne = findViewById<TextView>(R.id.box_one_text)
        val boxTwo = findViewById<TextView>(R.id.box_two_text)
        val boxThree = findViewById<TextView>(R.id.box_three_text)
        val boxFour = findViewById<TextView>(R.id.box_four_text)
        val boxFive = findViewById<TextView>(R.id.box_five_text)

        boxOneColor = sharedPreferences.getInt("boxOne", R.color.gray)
        boxTwoColor = sharedPreferences.getInt("boxTwo", R.color.gray)
        boxThreeColor = sharedPreferences.getInt("boxThree", R.color.gray)
        boxFourColor = sharedPreferences.getInt("boxFour", R.color.gray)
        boxFiveColor = sharedPreferences.getInt("boxFive", R.color.gray)

        boxOne.setBackgroundResource(boxOneColor)
        boxTwo.setBackgroundResource(boxTwoColor)
        boxThree.setBackgroundResource(boxThreeColor)
        boxFour.setBackgroundResource(boxFourColor)
        boxFive.setBackgroundResource(boxFiveColor)

        btnRed.setOnClickListener{
            pencil = R.color.red
        }

        btnGreen.setOnClickListener{
            pencil = R.color.green
        }

        btnYellow.setOnClickListener{
            pencil = R.color.yellow
        }

        boxOne.setOnClickListener{
            boxOne.setBackgroundResource(pencil)
            boxOneColor = pencil
        }

        boxTwo.setOnClickListener{
            boxTwo.setBackgroundResource(pencil)
            boxTwoColor = pencil
        }
        boxThree.setOnClickListener{
            boxThree.setBackgroundResource(pencil)
            boxThreeColor = pencil
        }
        boxFour.setOnClickListener{
            boxFour.setBackgroundResource(pencil)
            boxFourColor = pencil
        }
        boxFive.setOnClickListener{
            boxFive.setBackgroundResource(pencil)
            boxFiveColor = pencil
        }

    }

    override fun onStop() {
        super.onStop()

        val editor = sharedPreferences.edit()

        editor.putInt("boxOne", boxOneColor)
        editor.putInt("boxTwo", boxTwoColor)
        editor.putInt("boxThree", boxThreeColor)
        editor.putInt("boxFour", boxFourColor)
        editor.putInt("boxFive", boxFiveColor)

        editor.commit()
    }
}