package com.example.assignment_examapp

data class MCQ(
    var question:String,
    var option1: String,
    var option2: String,
    var option3: String,
    var option4: String,
    var answerList: List<Boolean>,
    var isattempted : Boolean = false
)
