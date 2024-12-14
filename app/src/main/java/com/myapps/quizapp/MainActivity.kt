package com.myapps.quizapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.provider.SyncStateContract.Constants
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }
        val start_btn : Button = findViewById(R.id.start_btn)
        val nameinput : EditText = findViewById(R.id.etname)
        start_btn.setOnClickListener{
            if(nameinput.text.isEmpty()){
                Toast.makeText(this,"Enter your name",Toast.LENGTH_SHORT).show()
                errvibration()
            }
            else{
                val intent= Intent(this,QuizQuestionsActivity::class.java)
                intent.putExtra(All_Questions.USER_NAME,nameinput.text.toString())
                startActivity(intent)
                vibration()
            }
        }
    }

     fun vibration()
     {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        vibrator.vibrate(vibrationEffect)
    }

    private fun errvibration()
    {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect = VibrationEffect.createOneShot(20,60)
        vibrator.vibrate(vibrationEffect)
    }


}