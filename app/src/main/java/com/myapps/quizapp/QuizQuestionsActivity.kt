package com.myapps.quizapp

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.service.autofill.OnClickAction
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text
import kotlin.reflect.typeOf

class QuizQuestionsActivity : AppCompatActivity(),OnClickListener
{
    private var questionList: ArrayList<Question>? = null
    private var currentpos = 1
    private var mSelectedOption = 0
    private var mUsername:String?=null
    private var correctans:Int = 0

    private var questionview: TextView? = null
    private var flagimg: ImageView? = null
    private var progressbar: ProgressBar? = null
    private var progressnum: TextView? = null
    private var submit_btn: Button? = null

    private var optionOne: TextView? = null
    private var optionTwo: TextView? = null
    private var optionThree: TextView? = null
    private var optionFour: TextView? = null
    private var submit_anyoption = 0

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz_questions)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        questionList = All_Questions.getQuestions()
        progressbar = findViewById(R.id.progress_bar)
        progressnum = findViewById(R.id.progressNum)
        questionview = findViewById(R.id.question)
        flagimg = findViewById(R.id.imageView)
        submit_btn = findViewById(R.id.submitButton)

        optionOne = findViewById(R.id.optionOne)
        optionTwo = findViewById(R.id.optionTwo)
        optionThree = findViewById(R.id.optionThree)
        optionFour = findViewById(R.id.optionFour)

        optionOne?.setOnClickListener(this)
        optionTwo?.setOnClickListener(this)
        optionThree?.setOnClickListener(this)
        optionFour?.setOnClickListener(this)
        submit_btn?.setOnClickListener(this)
        mUsername=intent.getStringExtra(All_Questions.USER_NAME)

        setQuestion()
    }

    private fun vibration()
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

    private fun setQuestion()
    {
        defaultOptionViews()
        val que: Question = questionList!![currentpos - 1]
        progressbar?.progress = currentpos
        var progress_num_text = "$currentpos/${progressbar?.max}"
        progressnum?.text = progress_num_text
        questionview?.text = que.question
        flagimg?.setImageResource(que.flag_img)
        optionOne?.text = que.option1
        optionTwo?.text = que.option2
        optionThree?.text = que.option3
        optionFour?.text = que.option4

        if (currentpos == questionList!!.size)
            submit_btn?.text = "FINISH"
        else
            submit_btn?.text = "SUBMIT"
    }

    private fun defaultOptionViews()
    {
        val options = ArrayList<TextView>()
        optionOne?.let { options.add(0, it) }
        optionTwo?.let { options.add(1, it) }
        optionThree?.let { options.add(2, it) }
        optionFour?.let { options.add(3, it) }

        for (option in options) {
            option.setTextColor(Color.parseColor("#000000"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.optionsbg)
        }

    }

    private fun selectedOptionview(tv: TextView, selectedoption: Int)
    {
        defaultOptionViews()
        mSelectedOption = selectedoption
        tv.setTextColor(Color.parseColor("#C434EF"))
        tv.setTypeface(tv.typeface, Typeface.BOLD)
        tv.background = ContextCompat.getDrawable(this, R.drawable.selectedoptionsbg)

    }

    override fun onClick(view: View?)
    {
        when (view?.id) {
            R.id.optionOne -> {
                optionOne?.let {
                    selectedOptionview(it, 1)
                    vibration()
                }
            }

            R.id.optionTwo -> {
                optionTwo?.let {
                    selectedOptionview(it, 2)
                    vibration()
                }
            }

            R.id.optionThree -> {
                optionThree?.let {
                    selectedOptionview(it, 3)
                    vibration()
                }
            }

            R.id.optionFour -> {
                optionFour?.let {
                    selectedOptionview(it, 4)
                    vibration()
                }
            }

            R.id.submitButton ->
            {
                vibration()
                if(mSelectedOption==0)
                {
                    currentpos++
                    vibration()
                    when
                    {
                        currentpos <= questionList!!.size ->
                        {
                            setQuestion()
                        }
                        else->
                        {
                            Toast.makeText(this,"Made it to the end",Toast.LENGTH_SHORT).show()
                            val intent=Intent(this,ResultScreen::class.java)
                            intent.putExtra(All_Questions.CORRECT_ANS,correctans)
                            intent.putExtra(All_Questions.USER_NAME,mUsername)
                            intent.putExtra(All_Questions.TOTAL_QUESTIONS,questionList?.size)
                            startActivity(intent)
                            finish()
                        }
                    }
                }
                else
                {
                    val question = questionList?.get(currentpos-1)
                    if (question!!.correctoption!=mSelectedOption)
                    {
                        answer(mSelectedOption,R.drawable.wrong_option)
                        //errvibration()
                    }
                    else{
                        correctans++
                    }
                    answer(question.correctoption,R.drawable.correct_option)

                    if(currentpos==questionList!!.size)
                        submit_btn?.text="FINISH"
                    else
                        submit_btn?.text="Next Question"

                    mSelectedOption=0
                }
            }

        }
    }

    private fun answer(answer: Int, drawable: Int)
    {
        when (answer) {
            1 -> {
                optionOne?.background = ContextCompat.getDrawable(this, drawable)
                optionOne?.setTextColor(Color.parseColor("#FFFFFF"))
                optionOne?.setTypeface(optionOne?.typeface,Typeface.BOLD)
            }

            2 -> {
                optionTwo?.background = ContextCompat.getDrawable(this, drawable)
                optionTwo?.setTextColor(Color.parseColor("#FFFFFF"))
                optionTwo?.setTypeface(optionTwo?.typeface,Typeface.BOLD)
            }

            3 -> {
                optionThree?.background = ContextCompat.getDrawable(this, drawable)
                optionThree?.setTextColor(Color.parseColor("#FFFFFF"))
                optionThree?.setTypeface(optionThree?.typeface,Typeface.BOLD)
            }

            4 -> {
                optionFour?.background = ContextCompat.getDrawable(this, drawable)
                optionFour?.setTextColor(Color.parseColor("#FFFFFF"))
                optionFour?.setTypeface(optionFour?.typeface,Typeface.BOLD)
            }
        }

    }

}

