package com.example.assignment_examapp

import android.app.Dialog
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
import androidx.lifecycle.lifecycleScope
import com.example.assignment_examapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    companion object{
        var marksCounter = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Q1
        var ques1attempted: Boolean = false
        binding.quesbtn1.setOnClickListener {
            // condition that will ensure that no further attempt once choice is submitted
            if (ques1attempted) {
                Toast.makeText(this, "You have attempted this question", Toast.LENGTH_LONG).show()
                return@setOnClickListener // this method exits the binding.quesbtn1.setOnClickListener
            }

            //Dialog Build + logic Code
            val options = arrayOf("Immutable sequence","Mutable sequence", "Ordered and unique","Unordered and fixed")
            var selectedOption: Int = -1
            
            val dialogBuilder1 = AlertDialog.Builder(this)
            dialogBuilder1.setTitle("Ques 1 :${getString(R.string.python_ques1)}")
            dialogBuilder1.setSingleChoiceItems(options, -1, DialogInterface.OnClickListener { dialogInterface, optionno ->
                    //what to do when a option is selected
                    selectedOption = optionno
                })
            dialogBuilder1.setPositiveButton("Submit", DialogInterface.OnClickListener { dialogInterface, i ->
                    // what will happen when positive button is clicked
                    if (selectedOption == 1) {
                        marksCounter += 10
                    } else if (selectedOption >= 0) {
                        marksCounter -= 2
                    } else {
                        
                    }
                ques1attempted = true
                // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
                })
            dialogBuilder1.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                    //  what will happen when cancel option is clicked

                })
            dialogBuilder1.show()
        }

//      Q2
        var ques2attempted: Boolean = false
        binding.quesbtn2.setOnClickListener {
            // condition that will ensure that no further attempt once choice is submitted
            if (ques2attempted) {
                Toast.makeText(this, "You have attempted this question", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Dialog Build + logic Code
            val options = arrayOf("function","def","func","define")
            var selectedOption: Int = -1
            
            val dialogBuilder1 = AlertDialog.Builder(this)
            dialogBuilder1.setTitle("Ques 2 :${getString(R.string.python_ques2)}")
            dialogBuilder1.setSingleChoiceItems(options, -1, DialogInterface.OnClickListener { dialogInterface, optionno ->
                    //what to do when a option is selected
                    selectedOption = optionno
                })
            dialogBuilder1.setPositiveButton("Submit", DialogInterface.OnClickListener { dialogInterface, i ->
                    // what will happen when positive button is clicked
                    if (selectedOption == 1) {
                        marksCounter += 10
                    } else if (selectedOption >= 0) {
                        marksCounter -= 2
                    } else {
                        
                    }
                ques2attempted = true
                // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
                })
            dialogBuilder1.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                    //  what will happen when cancel option is clicked

                })
            dialogBuilder1.show()
        }

//      Q3
        var ques3attempted: Boolean = false
        binding.quesbtn3.setOnClickListener {
            // condition that will ensure that no further attempt once choice is submitted
            if (ques3attempted) {
                Toast.makeText(this, "You have attempted this question", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Dialog Build + logic Code
            val options = arrayOf("#Comment","// Comment","/* Comment */","-- Comment")
            var selectedOption: Int = -1

            val dialogBuilder1 = AlertDialog.Builder(this)
            dialogBuilder1.setTitle("Ques 3 :${getString(R.string.python_ques2)}")
            dialogBuilder1.setSingleChoiceItems(options, -1, DialogInterface.OnClickListener { dialogInterface, optionno ->
                    //what to do when a option is selected
                    selectedOption = optionno
                })
            dialogBuilder1.setPositiveButton("Submit", DialogInterface.OnClickListener { dialogInterface, i ->
                    // what will happen when positive button is clicked
                    if (selectedOption == 0) {
                        marksCounter += 10
                    } else if (selectedOption >= 0) {
                        marksCounter -= 2
                    } else {

                    }
                ques3attempted = true
                // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
                })
            dialogBuilder1.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                    //  what will happen when cancel option is clicked

                })
            dialogBuilder1.show()
        }

//      Q4
        var ques4attempted: Boolean = false
        binding.quesbtn4.setOnClickListener {
            // condition that will ensure that no further attempt once choice is submitted
            if (ques4attempted) {
                Toast.makeText(this, "You have attempted this question", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Dialog Build + logic Code
            val options = arrayOf("int","list","tuple","dict")
            val correctAnswers = booleanArrayOf(false, true, false, true) // Only "int" is correct
            val selectedOptions = BooleanArray(options.size) // Tracks user selection

            val dialogBuilder1 = AlertDialog.Builder(this)
            dialogBuilder1.setTitle("Ques 4 :${getString(R.string.python_ques2)}")
            dialogBuilder1.setMultiChoiceItems(options, null, DialogInterface.OnMultiChoiceClickListener { dialogInterface, optionno, ischecked ->
                //what to do when a option is selected
                selectedOptions[optionno] = ischecked
            })
            dialogBuilder1.setPositiveButton("Submit", DialogInterface.OnClickListener { dialogInterface, i ->
                // what will happen when positive button is clicked
                val isCorrect = selectedOptions.contentEquals(correctAnswers)
                if (isCorrect) {
                    marksCounter += 10
                } else if (selectedOptions.any { it }) {
                    marksCounter -= 2
                }
                ques4attempted = true
                // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
            })
            dialogBuilder1.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                //  what will happen when cancel option is clicked

            })
            dialogBuilder1.show()
        }
//      Q5
        var ques5attempted: Boolean = false
        binding.quesbtn5.setOnClickListener {
            // condition that will ensure that no further attempt once choice is submitted
            if (ques5attempted) {
                Toast.makeText(this, "You have attempted this question", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //Dialog Build + logic Code
            val options = arrayOf("len()","print()","factorial()","sqrt()")
            val correctAnswers = booleanArrayOf(true, true, false, false) // Only "int" is correct
            val selectedOptions = BooleanArray(options.size) // Tracks user selection

            val dialogBuilder1 = AlertDialog.Builder(this)
            dialogBuilder1.setTitle("Ques 4 :${getString(R.string.python_ques2)}")
            dialogBuilder1.setMultiChoiceItems(options, null, DialogInterface.OnMultiChoiceClickListener { dialogInterface, optionno, ischecked ->
                //what to do when a option is selected
                selectedOptions[optionno] = ischecked
            })
            dialogBuilder1.setPositiveButton("Submit", DialogInterface.OnClickListener { dialogInterface, i ->
                // what will happen when positive button is clicked
                val isCorrect = selectedOptions.contentEquals(correctAnswers)
                if (isCorrect) {
                    marksCounter += 10
                } else if (selectedOptions.any { it }) {
                    marksCounter -= 2
                }
                ques5attempted = true
                // code used for testing if updating marks counter works or not
//                    binding.scoretv.text = "Marks scored : $marksCounter"
                    Log.d("Main Activity", marksCounter.toString())
            })
            dialogBuilder1.setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                //  what will happen when cancel option is clicked

            })
            dialogBuilder1.show()
        }
        binding.submitAttempt.setOnClickListener{
            var intent = Intent(this@MainActivity,ResultActivity::class.java)
            intent.putExtra("marksScored", marksCounter)
            startActivity(intent)
            finish()
        }
    }
}