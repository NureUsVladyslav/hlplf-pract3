package com.example.practicalwork3

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class Task2Activity : Activity() {

    private lateinit var numberEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var newGameButton: Button
    private lateinit var backButton: Button
    private lateinit var resultTextView: TextView
    private lateinit var attemptsTextView: TextView

    private var secretNumber = 0
    private var attempts = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task2)

        numberEditText = findViewById(R.id.numberEditText)
        checkButton = findViewById(R.id.checkButton)
        newGameButton = findViewById(R.id.newGameButton)
        backButton = findViewById(R.id.backButton)
        resultTextView = findViewById(R.id.resultTextView)
        attemptsTextView = findViewById(R.id.attemptsTextView)

        startNewGame()

        checkButton.setOnClickListener {
            checkNumber()
        }

        newGameButton.setOnClickListener {
            startNewGame()
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun startNewGame() {
        secretNumber = Random.nextInt(1, 11)
        attempts = 0

        numberEditText.text.clear()
        resultTextView.text = "Я загадав число від 1 до 10. Спробуйте вгадати!"
        attemptsTextView.text = "Кількість спроб: 0"
    }

    private fun checkNumber() {
        val userNumber = numberEditText.text.toString()
            .trim()
            .toIntOrNull()

        if (userNumber == null) {
            resultTextView.text = "Помилка: введіть число"
            return
        }

        if (userNumber < 1 || userNumber > 10) {
            resultTextView.text = "Число має бути від 1 до 10"
            return
        }

        attempts++
        attemptsTextView.text = "Кількість спроб: $attempts"

        resultTextView.text = when {
            userNumber < secretNumber -> "Загадане число більше"
            userNumber > secretNumber -> "Загадане число менше"
            else -> "Вітаю! Ви вгадали число $secretNumber за $attempts спроб(и)"
        }
    }
}