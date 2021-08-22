package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {


    private lateinit var f0: TextView
    private lateinit var f1: TextView
    private lateinit var f2: TextView
    private lateinit var f3: TextView
    private lateinit var f4: TextView
    private lateinit var f5: TextView
    private lateinit var f6: TextView
    private lateinit var f7: TextView
    private lateinit var f8: TextView


    private lateinit var statusText: TextView

    private var currentPlayer = "x"

    private var gameState = "playing"

    private lateinit var allFileds: Array<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        f0 = findViewById(R.id.f0)
        f1 = findViewById(R.id.f1)
        f2 = findViewById(R.id.f2)
        f3 = findViewById(R.id.f3)
        f4 = findViewById(R.id.f4)
        f5 = findViewById(R.id.f5)
        f6 = findViewById(R.id.f6)
        f7 = findViewById(R.id.f7)
        f8 = findViewById(R.id.f8)

        statusText = findViewById(R.id.startusText)

        allFileds = arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8)



        for (fields in allFileds) {
            fields.setOnClickListener {
                onFieldClick(it as TextView)
            }
        }

    }

    private fun onFieldClick(field: TextView) {
        if (gameState == "won") {
            resetGame()
            return
        }
        if (field.text == "" && gameState == "playing") {
            field.text = currentPlayer
            if (checkWin()) {
                statusText.text = "Spieler $currentPlayer hat gewonnen"
                println(checkWin())
                gameState = "won"
            } else if(allFileds.all { it.text == "" })else {
                currentPlayer = if (currentPlayer == "x") "o" else "x"
                statusText.text = "Spieler $currentPlayer ist an der Reihe"
            }
        }

    }

    private fun resetGame() {
        currentPlayer = "x"
        statusText.text = "Spieler $currentPlayer ist an der Reihe"
        gameState = "playing"



        for (field in allFileds) {
            field.text = ""
        }
    }

    private fun checkWin(): Boolean {
        val horizontal = (f0.text == f1.text && f2.text == f1.text && f0.text != "") ||
                (f3.text == f4.text && f4.text == f5.text && f3.text != "") ||
                (f6.text == f7.text && f7.text == f8.text && f6.text != "")

        val vertical = (f0.text == f3.text && f3.text == f6.text && f0.text != "") ||
                (f1.text == f4.text && f4.text == f7.text && f1.text != "") ||
                (f2.text == f5.text && f5.text == f8.text && f2.text != "")

        val diagonal = (f0.text == f4.text && f4.text == f8.text && f0.text != "") ||
                (f2.text == f4.text && f4.text == f6.text && f2.text != "")



        return horizontal || vertical || diagonal
    }
}