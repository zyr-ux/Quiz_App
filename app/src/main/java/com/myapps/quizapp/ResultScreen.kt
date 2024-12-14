package com.myapps.quizapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultScreen : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_result_screen)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        val nameView=findViewById<TextView>(R.id.textView_name)
        val score_view=findViewById<TextView>(R.id.textView_score)
        val finish_btn=findViewById<Button>(R.id.finishbtn)

        val mUsername = intent.getStringExtra(All_Questions.USER_NAME)
        val mCorrectans = intent.getIntExtra(All_Questions.CORRECT_ANS,0)
        val mTotalQuestions=intent.getIntExtra(All_Questions.TOTAL_QUESTIONS,0)

        nameView.text="$mUsername"
        score_view.text="Your Score is $mCorrectans out of $mTotalQuestions"

        finish_btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            vibration()
        }
    }

    private fun vibration()
    {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        vibrator.vibrate(vibrationEffect)
    }

}