package com.example.practicalwork3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Task4Activity : Activity() {

    private lateinit var firstRomanEditText: EditText
    private lateinit var secondRomanEditText: EditText
    private lateinit var resultTextView: TextView

    private val romanMap = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task4)

        firstRomanEditText = findViewById(R.id.firstRomanEditText)
        secondRomanEditText = findViewById(R.id.secondRomanEditText)
        resultTextView = findViewById(R.id.resultTextView)

        val addButton = findViewById<Button>(R.id.addButton)
        val subtractButton = findViewById<Button>(R.id.subtractButton)
        val multiplyButton = findViewById<Button>(R.id.multiplyButton)
        val divideButton = findViewById<Button>(R.id.divideButton)
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

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun calculate(operation: String) {
        val firstRoman = firstRomanEditText.text.toString()
            .trim()
            .uppercase()

        val secondRoman = secondRomanEditText.text.toString()
            .trim()
            .uppercase()

        val firstNumber = romanToInt(firstRoman)
        val secondNumber = romanToInt(secondRoman)

        if (firstNumber == null || secondNumber == null) {
            resultTextView.text = "Помилка: введіть коректні римські числа"
            return
        }

        if (operation == "/" && secondNumber == 0) {
            resultTextView.text = "Помилка: ділення на нуль неможливе"
            return
        }

        if (operation == "/" && firstNumber % secondNumber != 0) {
            resultTextView.text = "Помилка: результат ділення не є цілим числом"
            return
        }

        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            "-" -> firstNumber - secondNumber
            "*" -> firstNumber * secondNumber
            "/" -> firstNumber / secondNumber
            else -> 0
        }

        if (result <= 0) {
            resultTextView.text = "Помилка: римські числа не мають нуля або від'ємних значень"
            return
        }

        if (result > 3999) {
            resultTextView.text = "Помилка: результат більший за 3999"
            return
        }

        val resultRoman = intToRoman(result)

        resultTextView.text =
            "$firstRoman $operation $secondRoman = $resultRoman\n" +
                    "$firstNumber $operation $secondNumber = $result"
    }

    private fun romanToInt(roman: String): Int? {
        if (roman.isBlank()) {
            return null
        }

        for (symbol in roman) {
            if (!romanMap.containsKey(symbol)) {
                return null
            }
        }

        var result = 0
        var previousValue = 0

        for (i in roman.length - 1 downTo 0) {
            val currentValue = romanMap[roman[i]] ?: return null

            if (currentValue < previousValue) {
                result -= currentValue
            } else {
                result += currentValue
            }

            previousValue = currentValue
        }

        if (result <= 0 || result > 3999) {
            return null
        }

        val normalizedRoman = intToRoman(result)

        return if (normalizedRoman == roman) {
            result
        } else {
            null
        }
    }

    private fun intToRoman(number: Int): String {
        var value = number
        val result = StringBuilder()

        val romanValues = listOf(
            1000 to "M",
            900 to "CM",
            500 to "D",
            400 to "CD",
            100 to "C",
            90 to "XC",
            50 to "L",
            40 to "XL",
            10 to "X",
            9 to "IX",
            5 to "V",
            4 to "IV",
            1 to "I"
        )

        for ((arabicValue, romanValue) in romanValues) {
            while (value >= arabicValue) {
                result.append(romanValue)
                value -= arabicValue
            }
        }

        return result.toString()
    }
}