package com.sinx.generationtest

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import java.io.File

class MainActivity : AppCompatActivity() {

    private val counter = Counter()
    private lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val plusBtn = findViewById<View>(R.id.plusBtn)
        val minusBtn = findViewById<View>(R.id.minusBtn)
        val stopTest = findViewById<View>(R.id.stopTest)
        result = findViewById<TextView>(R.id.result)
        plusBtn.setOnClickListener {
            updateCounterByOperation(Operation.PLUS_ONE)
        }
        minusBtn.setOnClickListener {
            updateCounterByOperation(Operation.MINUS_ONE)
        }
        stopTest.setOnClickListener {
            closeTest(dataLogPath)
        }
    }

    private fun updateCounterByOperation(operation: Operation) {
        result.text = counter.change(operation)
    }

    val dataLogPath: String
        get() {
            val pathname = dataDir.absolutePath + File.separator + "tests"
            val file = File(pathname)
            if (!file.exists())
                file.mkdirs()
            return pathname
        }
}