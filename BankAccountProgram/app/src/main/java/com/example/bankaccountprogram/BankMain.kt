package com.example.bankaccountprogram

fun main(){
    val kimBankAccount = BankAccount("kim",1300.20)
    kimBankAccount.deposit(200.0)
    kimBankAccount.displayTransactionHistory()
    kimBankAccount.withdraw(20000.0)
    kimBankAccount.displayTransactionHistory()
    println("${kimBankAccount.accountHolder}의 통장 잔고는 ${kimBankAccount.balance}입니다")
}