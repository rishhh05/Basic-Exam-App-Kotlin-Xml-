package com.example.assignment_examapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assignment_examapp.MainActivity.Companion.marksCounter
import com.example.assignment_examapp.databinding.ActivityDbmsBinding

class DbmsActivity : AppCompatActivity() {
    lateinit var binding: ActivityDbmsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDbmsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var ques1 = MCQ(getString(R.string.dbms_ques1), "Structured Query Language","Simple Query Logic",
            "System Query Language", "Sequential Query Logic", listOf(true,false,false,false))
        var ques2 = MCQ(getString(R.string.dbms_ques2)," REMOVE TABLE\n","DROP TABLE",
            "DELETE TABLE" ,"TRUNCATE TABLE", listOf(false,true,false,false))
        var ques3 = MCQ(getString(R.string.dbms_ques3),"Concurrency","Atomicity" ,"Security" ,"Performance",
            listOf(false,true,false,false)
        )
        var ques4 = MCQ(getString(R.string.dbms_ques4),"CREATE","INSERT","ALTER","UPDATE",
            listOf(true,false,true,false)
        )
        var ques5 = MCQ(getString(R.string.dbms_ques5),"COUNT()" ,"SUM()" ,"SELECT()","AVG()",
            listOf(true,true,false,true)
        )

        binding.quesbtn1.setOnClickListener{
            showQuesDialog(ques1)
        }
        binding.quesbtn2.setOnClickListener{
            showQuesDialog(ques2)
        }
        binding.quesbtn3.setOnClickListener{
            showQuesDialog(ques3)
        }
        binding.quesbtn4.setOnClickListener{
            showQuesDialog(ques4)
        }
        binding.quesbtn5.setOnClickListener{
            showQuesDialog(ques5)
        }
        binding.prevSecBtn.setOnClickListener{
            val intent = Intent(this@DbmsActivity, MainActivity::class.java)
            startActivity(intent)
        }

        binding.submitAttempt.setOnClickListener{
            val intent = Intent(this@DbmsActivity, ResultActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showQuesDialog(ques: MCQ) {
        if(ques.isattempted){
            Toast.makeText(this, "You have attempted this question", Toast.LENGTH_LONG).show()
            return
        }
        var options = arrayOf(ques.option1,ques.option2,ques.option3,ques.option4)
        var builder = AlertDialog.Builder(this)
        var answerList = ques.answerList

        // For single choice mcq
        if (ques.answerList.count { it } == 1){
            var selectedOption = -1
            builder
                .setTitle(ques.question)
                .setSingleChoiceItems(options, -1, DialogInterface.OnClickListener { dialogInterface, optionNo ->
                    //what to do when a option is selected
                    selectedOption = optionNo
                })
                .setPositiveButton("Submit",DialogInterface.OnClickListener { dialogInterface, i ->
                    // what will happen if the submit button is clicked
                    val isCorrect = ques.answerList[selectedOption]  // This checks if the selected option is marked true
                    if (isCorrect) {
                        marksCounter += 10
                    } else {
                        marksCounter -= 2
                    }
                    ques.isattempted = true
                    // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                    // what will happen in the cancel button is clicked

                })
                .show()
        }
        // for multiple choice mcq
        else{
            var selectedOption = BooleanArray(options.size) // this will be used as a list to compare bw marked ans
            builder
                .setTitle(ques.question)
                .setMultiChoiceItems(options,null,DialogInterface.OnMultiChoiceClickListener { dialogInterface, optionNo, ischecked ->
                    selectedOption[optionNo] = ischecked
                })
                .setPositiveButton("Submit",DialogInterface.OnClickListener { dialogInterface, i ->
                    val isCorrect = answerList.toList() == selectedOption.toList()
                    if (isCorrect) {
                        marksCounter += 10
                    } else if (selectedOption.any { it }) {
                        marksCounter -= 2
                    }
                    else{

                    }
                    ques.isattempted = true
                    // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener { dialogInterface, i ->  })

                .show()
        }
    }
}