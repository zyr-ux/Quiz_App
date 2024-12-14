package com.myapps.quizapp

object All_Questions
{
    const val USER_NAME:String="user_name"
    const val CORRECT_ANS:String="correct_ans"
    const val TOTAL_QUESTIONS:String="total_questions"

    fun getQuestions():ArrayList<Question>
    {
        val questionList=ArrayList<Question>()
        val que1=Question(
            1,
            "Identify the flag",
            "Brazil",
            "India",
            "USA",
            "Argentina",
            4,
            R.drawable.argentina
        )
        questionList.add(que1)

        val que2=Question(
            2,
            "Identify the flag",
            "South Korea",
            "Japan",
            "North Korea",
            "Taiwan",
            1,
            R.drawable.skorea
        )
        questionList.add(que2)

        val que3=Question(
            3,
            "Identify the flag",
            "New Zealand",
            "Panama",
            "UK",
            "Australia",
            4,
            R.drawable.australia
        )
        questionList.add(que3)

        val que4=Question(
            4,
            "Identify the flag",
            "Spain",
            "Brazil",
            "Portugal",
            "Chile",
            2,
            R.drawable.brazil
        )
        questionList.add(que4)

        val que5=Question(
            5,
            "Identify the flag",
            "UK",
            "Brazil",
            "France",
            "Scotland",
            3,
            R.drawable.france
        )
        questionList.add(que5)

        val que6=Question(
            6,
            "Identify the flag",
            "Ireland",
            "Brazil",
            "India",
            "Malaysia",
            3,
            R.drawable.india
        )
        questionList.add(que6)

        val que7=Question(
            7,
            "Identify the flag",
            "Palestine",
            "Jordan",
            "Qatar",
            "Egypt",
            1,
            R.drawable.palesine
        )
        questionList.add(que7)

        val que8=Question(
            8,
            "Identify the flag",
            "Pakistan",
            "Sri Lanka",
            "Singapore",
            "Bangladesh",
            2,
            R.drawable.srilanka
        )
        questionList.add(que8)

        val que9=Question(
            9,
            "Identify the flag",
            "Finland",
            "Spain",
            "Czech Republic",
            "Sweden",
            2,
            R.drawable.spain
        )
        questionList.add(que9)

        val que10=Question(
            10,
            "Identify the flag",
            "Romania",
            "Iceland",
            "Norway",
            "Italy",
            3,
            R.drawable.norway
        )
        questionList.add(que10)

        return questionList
    }

}