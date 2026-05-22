package com.example.practicalwork3

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val task1Button = findViewById<Button>(R.id.task1Button)
        val task2Button = findViewById<Button>(R.id.task2Button)
        val task3Button = findViewById<Button>(R.id.task3Button)
        val task4Button = findViewById<Button>(R.id.task4Button)

        task1Button.setOnClickListener {
            val intent = Intent(this, Task1Activity::class.java)
            startActivity(intent)
        }

        task2Button.setOnClickListener {
            val intent = Intent(this, Task2Activity::class.java)
            startActivity(intent)
        }

        task3Button.setOnClickListener {
            val intent = Intent(this, Task3Activity::class.java)
            startActivity(intent)
        }

        task4Button.setOnClickListener {
            val intent = Intent(this, Task4Activity::class.java)
            startActivity(intent)
        }
    }

    private fun showNotReadyMessage() {
        Toast.makeText(this, "Завдання ще не реалізовано", Toast.LENGTH_SHORT).show()
    }
}