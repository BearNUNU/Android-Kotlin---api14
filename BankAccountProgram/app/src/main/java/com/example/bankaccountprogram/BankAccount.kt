package com.example.bankaccountprogram

class BankAccount(var accountHolder: String, var balance: Double) {
    private val transactionHistory = mutableListOf<String>("")
    fun deposit(amount: Double) {
        balance += amount
        transactionHistory.add("$accountHolder deposited $$amount -- $balance")
    }

    fun withdraw(amount: Double) {
        if(amount <= balance) {
            balance -= amount
            transactionHistory.add("$accountHolder withdraw $$amount -- $balance")
        } else{
            println("잔액이 부족합니다.")
        }
    }

    fun displayTransactionHistory() {
        for(transaction in transactionHistory){
            println(transaction)
        }
    }
}