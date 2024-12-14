package com.myapps.quizapp

data class Question(
    val id:Int,
    val question: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val correctoption: Int,
    val flag_img: Int
)
