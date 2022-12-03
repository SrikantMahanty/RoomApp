package com.example.roomapp


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.roomapp.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var appdb:AppDatabase
    private lateinit var  binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appdb=AppDatabase.getDatabase(this)


        binding.button.setOnClickListener{
            writeData()
        }

        binding.button2.setOnClickListener{
            readData()
        }

    }

    private fun writeData(){

        val firstName=binding.etFirstname.text.toString()
        val lastName=binding.etLastname.text.toString()
        val rollNo=binding.etRollno.text.toString()


        if(firstName.isNotEmpty() && lastName.isNotEmpty() && rollNo.isNotEmpty()){
           val std=Student(
               null,firstName,lastName,rollNo.toInt()
           )
            GlobalScope.launch(Dispatchers.IO){
                appdb.StudentDao().insert(std)
            }
            binding.etFirstname.text.clear()
            binding.etLastname.text.clear()
            binding.etRollno.text.clear()
            Toast.makeText(this@MainActivity,"Successfully written",Toast.LENGTH_SHORT).show()

        }else{
            Toast.makeText(this@MainActivity,"Please Enter Data",Toast.LENGTH_SHORT).show()
        }


    }
    private suspend fun displayData(student: Student){
        withContext(Dispatchers.Main){
            binding.textView2.text=student.firstName
            binding.textView3.text=student.lastName
            binding.textView4.text=student.rollNo.toString()
        }

    }
    private  fun readData(){
        val rollno=binding.etRollno1.text.toString()
        if (rollno.isNotEmpty()){
            lateinit var student: Student
            GlobalScope.launch {
                student=appdb.StudentDao().findByRoll(rollno.toInt())
                displayData(student)
            }

        }else
            Toast.makeText(this@MainActivity,"Please Enter Data",Toast.LENGTH_SHORT).show()






    }
}