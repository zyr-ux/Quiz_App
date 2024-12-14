package com.myapps.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ResultScreen : AppCompatActivity()
{
    private lateinit var transitionDrawable: TransitionDrawable
    private val handler = Handler(Looper.getMainLooper())
    private var isReversed = false

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

        val resultScreen=findViewById<ConstraintLayout>(R.id.main)
        val score="Your Score is $mCorrectans out of $mTotalQuestions"

        nameView.text="$mUsername"
        score_view.text=score

        finish_btn.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
            vibration()
        }

        transitionDrawable = AppCompatResources.getDrawable(this,R.drawable.gradient_list) as TransitionDrawable
        resultScreen.background=transitionDrawable
        startContinuousTransition()
    }

    private fun vibration()
    {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        val vibrationEffect = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK)
        vibrator.vibrate(vibrationEffect)
    }

    private fun startContinuousTransition()
    {
        val transitionDuration = 3000L // Transition duration in milliseconds
        val delayBetweenTransitions = 1000L // Delay before reversing in milliseconds

        // Runnable for managing the transitions
        val transitionRunnable = object : Runnable
        {
            override fun run()
            {
                if (isReversed)
                {
                    transitionDrawable.reverseTransition(transitionDuration.toInt())
                }
                else
                {
                    transitionDrawable.startTransition(transitionDuration.toInt())
                }
                isReversed = !isReversed

                // Schedule the next transition
                handler.postDelayed(this, transitionDuration + delayBetweenTransitions)
            }
        }

        // Start the animation loop
        handler.post(transitionRunnable)
    }

    override fun onDestroy()
    {
        super.onDestroy()
        // Stop the handler when the activity is destroyed
        handler.removeCallbacksAndMessages(null)
    }
}