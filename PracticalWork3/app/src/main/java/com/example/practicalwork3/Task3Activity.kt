package com.example.practicalwork3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlin.math.pow

class Task3Activity : Activity() {

    private lateinit var firstNumberEditText: EditText
    private lateinit var secondNumberEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var historyTextView: TextView

    private val historyFileName = "calculator_history.txt"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task3)

        firstNumberEditText = findViewById(R.id.firstNumberEditText)
        secondNumberEditText = findViewById(R.id.secondNumberEditText)
        resultTextView = findViewById(R.id.resultTextView)
        historyTextView = findViewById(R.id.historyTextView)

        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
        val remainderButton = findViewById<Button>(R.id.remainderButton)
        val powerButton = findViewById<Button>(R.id.powerButton)
        val showHistoryButton = findViewById<Button>(R.id.showHistoryButton)
        val clearHistoryButton = findViewById<Button>(R.id.clearHistoryButton)
        val backButton = findViewById<Button>(R.id.backButton)

        addButton.setOnClickListener {
            calculate("+")
        }

        subtractButton.setOnClickListener {
            calculate("-")
        }

        multiplyButton.setOnClickListener {
            calculate("*")
        }

        divideButton.setOnClickListener {
            calculate("/")
        }

        remainderButton.setOnClickListener {
            calculate("%")
        }

        powerButton.setOnClickListener {
            calculate("^")
        }

        showHistoryButton.setOnClickListener {
            showHistory()
        }

        clearHistoryButton.setOnClickListener {
            clearHistory()
        }

        backButton.setOnClickListener {
            finish()
        }

        showHistory()
    }

    private fun calculate(operation: String) {
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

        if ((operation == "/" || operation == "%") && secondNumber == 0.0) {
            resultTextView.text = "Помилка: ділення на нуль неможливе"
            return
        }

        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            "%" -> firstNumber % secondNumber
            "^" -> firstNumber.pow(secondNumber)
            else -> 0.0
        }

        val operationText = "${formatNumber(firstNumber)} $operation ${formatNumber(secondNumber)} = ${formatNumber(result)}"

        resultTextView.text = "Результат: $operationText"

        saveOperationToFile(operationText)
        showHistory()
    }

    private fun saveOperationToFile(operationText: String) {
        openFileOutput(historyFileName, MODE_APPEND).use { file ->
            file.write((operationText + "\n").toByteArray())
        }

        Toast.makeText(this, "Операцію збережено у файл", Toast.LENGTH_SHORT).show()
    }

    private fun showHistory() {
        val history = try {
            openFileInput(historyFileName).bufferedReader().use { reader ->
                reader.readText()
            }
        } catch (e: Exception) {
            ""
        }

        historyTextView.text = if (history.isBlank()) {
            "Історія операцій порожня"
        } else {
            history
        }
    }

    private fun clearHistory() {
        openFileOutput(historyFileName, MODE_PRIVATE).use { file ->
            file.write("".toByteArray())
        }

        historyTextView.text = "Історія операцій порожня"
        resultTextView.text = "Історію очищено"
    }

    private fun formatNumber(number: Double): String {
        return if (number % 1.0 == 0.0) {
            number.toLong().toString()
        } else {
            number.toString()
        }
    }
}