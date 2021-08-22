package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // init the TextViews they will show x or o also they are clickable
    private lateinit var f0: TextView
    private lateinit var f1: TextView
    private lateinit var f2: TextView
    private lateinit var f3: TextView
    private lateinit var f4: TextView
    private lateinit var f5: TextView
    private lateinit var f6: TextView
    private lateinit var f7: TextView
    private lateinit var f8: TextView

    // init the Win Lose (Lable)
    private lateinit var statusText: TextView

    // witch turn is it
    private var currentPlayer = "x"

    // will give a the information if the game is over
    private var gameState = "playing"

    // init all TextViews to create in a loop the Textviews
    private lateinit var allFileds: Array<TextView>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init the TextView by the ID
        f0 = findViewById(R.id.f0)
        f1 = findViewById(R.id.f1)
        f2 = findViewById(R.id.f2)
        f3 = findViewById(R.id.f3)
        f4 = findViewById(R.id.f4)
        f5 = findViewById(R.id.f5)
        f6 = findViewById(R.id.f6)
        f7 = findViewById(R.id.f7)
        f8 = findViewById(R.id.f8)

        // init the status by id
        statusText = findViewById(R.id.startusText)

        // init the array with the TextView that will show the chosen symbol
        allFileds = arrayOf(f0, f1, f2, f3, f4, f5, f6, f7, f8)


        // set click listener for all TextView that can be play on
        for (fields in allFileds) {
            fields.setOnClickListener {
                // in here is the game logic
                onFieldClick(it as TextView)
            }
        }

    }


    // the function becomes a TextView, the Symbol will set on this and the status will change
    private fun onFieldClick(field: TextView) {
        // check if the game is over
        if (gameState == "won") {
            // when it is over it will start an new Game after clicking on a TextView
            resetGame()
            return
        }

        // proof if the TextView have already an Symbol on
        if (field.text == "" && gameState == "playing") {

            //the TextView become the Symbol of the current Player and show it
            field.text = currentPlayer

            // when the Symbol is set the game need to check the win conditions
            if (checkWin()) {

                // when the Play has won the game it will show on the status TextView
                statusText.text = "Spieler $currentPlayer hat gewonnen"

                // gameState will change to won needed to clean up all the Textview
                gameState = "won"

                // when there is no winner because bought Players stupid
            } else if (allFileds.all { it.text != "" }) {
                // nobody have won will shown in the status TextView
                statusText.text = "Niemand hat gewonnen!!"

                // will change to the Player x because it always will start with him
                currentPlayer = "x"

                // when a new game will start this will clean up all the TextViews
                gameState = "won"

            } else {
                // when there is no winner the player turn changes and in the state TextView
                currentPlayer = if (currentPlayer == "x") "o" else "x"
                statusText.text = "Spieler $currentPlayer ist an der Reihe"
            }
        }

    }

    // every thing will clean up after a played game
    private fun resetGame() {
        currentPlayer = "x"
        statusText.text = "Spieler $currentPlayer ist an der Reihe"
        gameState = "playing"

        for (field in allFileds) {
            field.text = ""
        }
    }


    // dump function need to made it more logic and mathematics but on this point it check
    // if there is a winner in the game.
    private fun checkWin(): Boolean {
        // check horizontal
        val horizontal = (f0.text == f1.text && f2.text == f1.text && f0.text != "") ||
                (f3.text == f4.text && f4.text == f5.text && f3.text != "") ||
                (f6.text == f7.text && f7.text == f8.text && f6.text != "")

        // check vertical
        val vertical = (f0.text == f3.text && f3.text == f6.text && f0.text != "") ||
                (f1.text == f4.text && f4.text == f7.text && f1.text != "") ||
                (f2.text == f5.text && f5.text == f8.text && f2.text != "")

        // check diagonal
        val diagonal = (f0.text == f4.text && f4.text == f8.text && f0.text != "") ||
                (f2.text == f4.text && f4.text == f6.text && f2.text != "")



        return horizontal || vertical || diagonal
    }
}