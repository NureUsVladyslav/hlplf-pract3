package com.example.practicalwork3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Task1Activity : Activity() {

    private lateinit var firstNumberEditText: EditText
    private lateinit var secondNumberEditText: EditText
    private lateinit var calculateButton: Button
    private lateinit var backButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task1)

        firstNumberEditText = findViewById(R.id.firstNumberEditText)
        secondNumberEditText = findViewById(R.id.secondNumberEditText)
        calculateButton = findViewById(R.id.calculateButton)
        backButton = findViewById(R.id.backButton)
        resultTextView = findViewById(R.id.resultTextView)

        calculateButton.setOnClickListener {
            calculateDifference()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun calculateDifference() {
        val firstNumber = firstNumberEditText.text.toString()
            .trim()
            .replace(',', '.')
            .toDoubleOrNull()

        val secondNumber = secondNumberEditText.text.toString()
            .trim()
            .replace(',', '.')
            .toDoubleOrNull()

        if (firstNumber == null || secondNumber == null) {
            resultTextView.text = "Помилка: введіть два числа"
            return
        }

        val difference = firstNumber - secondNumber
        resultTextView.text = "Різниця: ${formatNumber(difference)}"
    }

    private fun formatNumber(number: Double): String {
        return if (number % 1.0 == 0.0) {
            number.toLong().toString()
        } else {
            number.toString()
        }
    }
}