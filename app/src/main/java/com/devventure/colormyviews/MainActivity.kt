package com.devventure.colormyviews

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.devventure.colormyviews.databinding.ActivityMainBinding
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

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

    fun share(view: View) {
        val bitmap = getScreenShot(binding.container)
        if (bitmap != null) {
            saveScreenshot(bitmap)
        }
    }
    private fun getScreenShot(v: View): Bitmap? {
        var screenshot: Bitmap? = null

        try {
            screenshot =
                Bitmap.createBitmap(v.measuredWidth, v.measuredHeight/2, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(screenshot)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("GFG", "Failed to capture screenshot. Error: "+ e.message)
        }
        return screenshot
    }

    private fun saveScreenshot(bitmap: Bitmap) {
        val fileName = "${System.currentTimeMillis()}.jpg"
        var fos: OutputStream? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            this.contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                val imgUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                fos = imgUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            val imgDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imgDir, fileName)
            fos = FileOutputStream(image)
        }

        fos?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, R.string.image_captured, Toast.LENGTH_SHORT).show()
            val shareIntent = Intent()
            shareIntent.action = Intent.ACTION_SEND
            shareIntent.putExtra(Intent.EXTRA_TEXT, R.string.checkout_screenshot)
            shareIntent.type = "image/*"
            shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            startActivity(Intent.createChooser(shareIntent, R.string.share_via.toString()))
        }
    }



}