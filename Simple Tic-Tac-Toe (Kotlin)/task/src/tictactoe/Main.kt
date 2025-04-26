package tictactoe
import java.util.Scanner

const val X = 'X'
const val O = 'O'
const val EMPTY = '_'
const val MAX_MOVES = 9
const val EMPTY_GRID = "_________"
const val GAME_NOT_FINISHED = "Game not finished"
const val X_WINS ="X wins"
const val O_WINS = "O wins"
const val IMPOSSIBLE = "Impossible"
const val DRAW = "Draw"

fun main() {
    // write your code here
    //var counter = 0
   // var gameBoard: String = ""
    var tempString = ""
    var row1 = ""
    var row2 = ""
    var row3 = ""
    var gameStatus = GAME_NOT_FINISHED
    var gameBoard:MutableList<Char> = mutableListOf()
    tempString = EMPTY_GRID
    if (tempString.length != MAX_MOVES){
        println("not a valid input")
    }
    else {
        for (i in tempString.indices) {
            gameBoard.add(tempString[i])
        }
        printBoard(gameBoard)

        while (gameStatus == GAME_NOT_FINISHED) {
            gameBoard = playerXTurn(gameBoard)
            printBoard(gameBoard)
            gameStatus = checkGameState(gameBoard)
            if (gameStatus == X_WINS) {
                printBoard(gameBoard)
                println(X_WINS)
            } else if (gameStatus == DRAW) {
                printBoard(gameBoard)
                println(DRAW)

            } else if (gameStatus == IMPOSSIBLE) {
                printBoard(gameBoard)
                println(IMPOSSIBLE)
            }
            else {
                gameBoard = playerOTurn(gameBoard)
                printBoard(gameBoard)
                gameStatus = checkGameState(gameBoard)
                if (gameStatus == O_WINS) {
                    printBoard(gameBoard)
                    println(O_WINS)
                } else if (gameStatus == DRAW) {
                    printBoard(gameBoard)
                    println(DRAW)

                } else if (gameStatus == IMPOSSIBLE) {
                    printBoard(gameBoard)
                    println(IMPOSSIBLE)
                }
                else {
                    printBoard(gameBoard)
                }
            }




        }
    }
}


fun printBoard(board: MutableList<Char>){

    println("-".repeat(9))
    println("| " + board[0] + " " + board[1] + " " + board[2] + " |")
    println("| " + board[3] + " " + board[4] + " " + board[5] + " |")
    println("| " + board[6] + " " + board[7] + " " + board[8] + " |")
    println("-".repeat(9))
}
fun addXtoBoard(board: MutableList<Char>,rowPos:Int,colPos:Int):MutableList<Char> {
    if (rowPos == 1) {
        if (colPos == 1) {

            board[0] = X

        }
        if (colPos == 2) {
            board[1] = X
        }
        if (colPos == 3) {
            board[2] = X
        }

    }
      else if (rowPos == 2) {
            if (colPos == 1) {
                board[3] =X
            }
            if (colPos == 2) {
                board[4] =X
            }
            if (colPos == 3) {
                board[5] = X
            }
        }
      else if (rowPos == 3) {
            if (colPos == 1) {
                board[6] = X
            }
            if (colPos == 2) {
                board[7] = X
            }
            if (colPos == 3) {
                board[8] = X
            }
        }
        else{
            return board
        }

    return board
}
fun addOToBoard(board: MutableList<Char>,rowPos: Int,colPos: Int): MutableList<Char>{
    if (rowPos == 1) {
        if (colPos == 1) {

            board[0] = O

        }
        if (colPos == 2) {
            board[1] = O
        }
        if (colPos == 3) {
            board[2] = O
        }

    }
    else if (rowPos == 2) {
        if (colPos == 1) {
            board[3] =O
        }
        if (colPos == 2) {
            board[4] =O
        }
        if (colPos == 3) {
            board[5] = O

        }
    }
    else if (rowPos == 3) {
        if (colPos == 1) {
            board[6] = O
        }
        if (colPos == 2) {
            board[7] = O
        }
        if (colPos == 3) {
            board[8] = O
        }
    }
    else{
        return board
    }

    return board
}

fun checkGameState(board:MutableList<Char>):String{
    //val gameComplete = isFinished(board)
    val gameComplete = true
    if (gameComplete == false){
        return GAME_NOT_FINISHED
    }
    else{
        val row1:MutableList<Char> = mutableListOf(board[0],board[1],board[2])
        val row2:MutableList<Char> = mutableListOf(board[3],board[4],board[5])
        val row3:MutableList<Char> = mutableListOf(board[6],board[7],board[8])

        val col1:MutableList<Char> = mutableListOf(board[0],board[3],board[6])
        val col2:MutableList<Char> = mutableListOf(board[1],board[4],board[7])
        val col3:MutableList<Char> = mutableListOf(board[2],board[5],board[8])

        // diagonals
        val diagonal1: MutableList<Char> = mutableListOf(board[0],board[4],board[8])
        val diagonal2: MutableList<Char> = mutableListOf(board[2],board[4],board[6])


        val xWinState: MutableList<Boolean> = mutableListOf()
        xWinState.add(checkX(row1))
        xWinState.add(checkX(row2))
        xWinState.add(checkX(row3))
        xWinState.add(checkX(col1))
        xWinState.add(checkX(col2))
        xWinState.add(checkX(col3))
        xWinState.add(checkX(diagonal1))
        xWinState.add(checkX(diagonal2))


        val yWinState: MutableList<Boolean> = mutableListOf()
        yWinState.add(checkO(row1))
        yWinState.add(checkO(row2))
        yWinState.add(checkO(row3))
        yWinState.add(checkO(col1))
        yWinState.add(checkO(col2))
        yWinState.add(checkO(col3))
        yWinState.add(checkO(diagonal1))
        yWinState.add(checkO(diagonal2))


        val isValidGame = isValidVictory(xWinState,yWinState)
        if (isValidGame == false){
            return IMPOSSIBLE
        }
        var xWins = 0
        var yWins = 0
        for(i in yWinState.indices){
           if (yWinState[i] == true) {
               yWins++
           }
        }
        for(i in xWinState.indices){
            if(xWinState[i]== true){
                xWins++
            }
        }
        var numberOfX = 0
        var numberOfY = 0
        for(i in board.indices){
            if (board[i] == X){
                numberOfX++
            }
            if (board[i] == O){
                numberOfY++
            }
        }
        if(numberOfX - numberOfY > 1 || numberOfY - numberOfX > 1){
            return IMPOSSIBLE
        }
        else if(xWins > yWins){
            return X_WINS
        }
        else if(yWins > xWins){
            return O_WINS
        }
        else if(xWins == yWins && isFinished(board) == false){
            return GAME_NOT_FINISHED
        }
        else {
            return DRAW
        }

    }


}
fun isFinished(board: MutableList<Char>): Boolean{
    for (i in board.indices){
        if(board[i] == EMPTY){
            return false
        }
    }
    return true
}

//functions for checking x
fun checkX(board: MutableList<Char>): Boolean{
    var x = 0
    for (i in board.indices){
        if (board[i] == X){
            x++
        }

        else{
            return false
        }
    }
    if(x == 3){
        return true
    }
    else{
        return false
    }
}


//functions for checking y
fun checkO(board: MutableList<Char>): Boolean{
    var o = 0
    for (i in board.indices){
        if (board[i] == O){
            o++

        }
        else{
            return false
        }
    }

    if (o == 3){
        return true
    }
    else{
        return false
    }
}

//function to check the game result is valid
fun isValidVictory(xPlayer:MutableList<Boolean>,yPlayer:MutableList<Boolean>):Boolean{
    var xWins = 0
    var yWins = 0
    for(i in xPlayer.indices){
        if (xPlayer[i] == true){
            xWins++
        }
    }
    for(i in yPlayer.indices){
        if (yPlayer[i] == true){
            yWins++
        }
    }

    if (xWins >=1 && yWins >= 1){
        return false
    }
    else{
        return true
    }

}


//check if cell is empty
fun cellIsEmpty(board: MutableList<Char>,rowPos: Int,colPos:Int): Boolean{

    if(rowPos == 1 ){
        if (colPos == 1){
            if(board[0] != EMPTY ){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{

                return false
            }
        }
        if (colPos == 2){
            if(board[1] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{

                return false
            }
        }
        if (colPos == 3){
            if(board[2] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{

                return false
            }
        }
    }
    if(rowPos  == 2 ){
        if (colPos == 1){
            if(board[3] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{

                return false
            }
        }
        if (colPos == 2){
            if(board[4] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{
                return false
            }
        }
        if (colPos == 3){
            if(board[5]!= EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{
                return false
            }
        }
    }
    if(rowPos  == 3 ){
        if (colPos == 1){
            if(board[6] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{
                return false
            }
        }
        if (colPos == 2){
            if(board[7] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{
                return false
            }
        }
        if (colPos == 3){
            if(board[8] != EMPTY){
                println("This cell is occupied! Choose another one!")
                return true
            }
            else{
                return false
            }
        }
    }

    return false
}

// check if contains non numbers

// check boundary
// @return  is false if an invalid value
fun boundaryCheck(xPos:Int,yPos:Int): Boolean{
    if(xPos < 1 || xPos > 3 || yPos < 1 || yPos > 3){
        return false
    }

    return true
}
fun printBoard4(board: MutableList<Char>){
    val tempBoard = board
    for(i in tempBoard.indices){
       if (tempBoard[i]==EMPTY){
           tempBoard[i]=' '
       }
    }
    println("-".repeat(9))
    println("| " + tempBoard[0] + " " + tempBoard[1] + " " + tempBoard[2] + " |")
    println("| " + tempBoard[3] + " " + tempBoard[4] + " " + tempBoard[5] + " |")
    println("| " + tempBoard[6] + " " + tempBoard[7] + " " + tempBoard[8] + " |")
    println("-".repeat(9))


}
fun digitCheck(row:String,col:String):Boolean {
    var letter = true
    //check if sting contains non numbers
    for (i in 0 until row.length) {
        if (row[i].isDigit() == false) {
            letter = true
            break
        } else {
            letter = false
        }
    }
    for (i in 0 until col.length) {
        if (col[i].isDigit() == false) {
            letter = true
            break
        } else {
            letter = false
        }

    }
    if (letter == true) {
        println("You should enter numbers!")
    }
    return letter
}

fun playerXTurn(board: MutableList<Char>):MutableList<Char>{
    var gameBoard = board
    println("Enter Coordinates to place your X ie 1,1")
    var (rowS,colS) = readln().split(" ")
    var row : Int = 0
    var col : Int = 0
    var occupation = false
    var validCoordinate = false
    var letter = digitCheck(rowS,colS)
    if (letter == false){
        row = rowS.toInt()
        col = colS.toInt()

        validCoordinate = boundaryCheck(row,col)
        if(validCoordinate == true){
            occupation = cellIsEmpty(gameBoard,row,col)

            if(occupation == false){

                addXtoBoard(gameBoard,row,col)
            }
            else{
                letter = false
                validCoordinate = true
                occupation = true
            }
        }
    }

    while (occupation == true || validCoordinate == false || letter == true) {


        //   printBoard(gameBoard)
        val (rowS,colS) = readln().split(" ")

        val row: Int
        val col: Int
        letter = digitCheck(rowS, colS)
        if(letter == true){
            validCoordinate = false
            occupation = false
        }
        else{
            row = rowS.toInt()
            col = colS.toInt()
            validCoordinate = boundaryCheck(row, col)
            if(validCoordinate == false){
                occupation = true
            }
            else{
                occupation = cellIsEmpty(gameBoard, row, col)
                if(occupation == false ){
                    gameBoard = addXtoBoard(gameBoard,row,col)
                }
            }


        }


    }

    return gameBoard
}

fun playerOTurn(board: MutableList<Char>):MutableList<Char>{
    var gameBoard = board
    println("Enter Coordinates to place your O ie 1,1")
    var (rowS,colS) = readln().split(" ")
    var row : Int = 0
    var col : Int = 0
    var occupation = false
    var validCoordinate = false
    var letter = digitCheck(rowS,colS)
    if (letter == false){
        row = rowS.toInt()
        col = colS.toInt()

        validCoordinate = boundaryCheck(row,col)
        if(validCoordinate == true){
            occupation = cellIsEmpty(gameBoard,row,col)

            if(occupation == false){


                addOToBoard(gameBoard,row,col)
            }
            else{
                letter = false
                validCoordinate = true
                occupation = true
            }
        }
    }

    while (occupation == true || validCoordinate == false || letter == true) {


        //   printBoard(gameBoard)
        val (rowS,colS) = readln().split(" ")

        val row: Int
        val col: Int
        letter = digitCheck(rowS, colS)
        if(letter == true){
            validCoordinate = false
            occupation = false
        }
        else{
            row = rowS.toInt()
            col = colS.toInt()
            validCoordinate = boundaryCheck(row, col)
            if(validCoordinate == false){
                occupation = true
            }
            else{
                occupation = cellIsEmpty(gameBoard, row, col)
                if(occupation == false ){
                    gameBoard = addOToBoard(gameBoard,row,col)
                }
            }


        }


    }

    return gameBoard


}
