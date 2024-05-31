package com.example.rockpaperscissors

fun main(){
    var computerChoice: String = ""
    var userChoice : String = ""

    println("가위,바위,보 입력")
    userChoice = readln()
    println("사용자 선택:" + userChoice)
    val randomNumb = (1..3).random()
    if(randomNumb ===1){
        computerChoice = "가위"
        println("컴퓨터: 가위")
    } else if(randomNumb === 2){
        computerChoice = "바위"
        println("컴퓨터: 바위")
    } else {
        computerChoice = "보"
        println("컴퓨터: 보")
    }

    val winner = when{
        userChoice == computerChoice -> "무승부"
        userChoice == "바위" && computerChoice == "가위" -> "사용자"
        userChoice == "가위" && computerChoice == "보" -> "사용자"
        userChoice == "보" && computerChoice == "바위" -> "사용자"
        else -> "컴퓨터"
    }

    println("승자" + winner)
    println("$winner")

}