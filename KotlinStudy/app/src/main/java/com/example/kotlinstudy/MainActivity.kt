package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            //IO - Network, Main - Main Thread , Default - Heavy lift (Large List)
            CoroutineScope(IO).launch{// new coroutine scope.
                logMessage(getResultsExternal())
            }
        }

    }
    private suspend fun setText(textNew: String){
        //switching context
        withContext(Main){
            val exText =  text.text.toString() + textNew+"\n"
            text.text = exText
        }

    }

    private suspend fun getResultsExternal():String{
        logMessage("Job1") 
        delay(1000)
        setText("Result1")
        return "Result1"
    }


    private fun logMessage(message:String)
    {
        Log.d("MESSAGE_CORO",message)
    }
}
