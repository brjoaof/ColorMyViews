package com.devventure.colormyviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.devventure.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var pencil = R.color.gray
        val btnRed = binding.btnRed
        val btnGreen = binding.btnGreen
        val btnYellow = binding.btnYellow

        var boxOne = binding.boxOneText
        var boxTwo = binding.boxTwoText
        var boxThree = binding.boxThreeText
        var boxFour = binding.boxFourText
        var boxFive = binding.boxFiveText

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
        }

        boxTwo.setOnClickListener{
            boxTwo.setBackgroundResource(pencil)
        }
        boxThree.setOnClickListener{
            boxThree.setBackgroundResource(pencil)
        }
        boxFour.setOnClickListener{
            boxFour.setBackgroundResource(pencil)
        }
        boxFive.setOnClickListener{
            boxFive.setBackgroundResource(pencil)
        }

    }
}