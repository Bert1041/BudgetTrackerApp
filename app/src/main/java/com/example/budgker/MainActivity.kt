package com.example.budgker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgker.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding //binding variable
    private lateinit var transactions: ArrayList<Transaction>
    private lateinit var transactionAdapter: TransactionAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater) //binding content view
        setContentView(binding.root)

        transactions = arrayListOf(
            Transaction("Weekend budget", 400.00),
            Transaction("Bananas", -4.00),
            Transaction("Gasoline", -40.00),
            Transaction("Breakfast", -9.99),
            Transaction("Water bottles", -4.00),
            Transaction("Suncream", -8.00),
            Transaction("Car Park", -15.00)
        )

        transactionAdapter = TransactionAdapter(transactions)
        linearLayoutManager = LinearLayoutManager(this)


        binding.recyclerview.apply {
            adapter = transactionAdapter
            layoutManager = linearLayoutManager
        }
        updateDashboard()
    }
        private fun updateDashboard(){
            val totalAmount = transactions.map {it.amount}.sum()
            val budgetAmount =transactions.filter { it.amount>0 }.map{it.amount}.sum()
            val expenseAmount = totalAmount - budgetAmount

            binding.balance.text = "$ %.2f".format(totalAmount)
            binding.budget.text = "$ %.2f".format(budgetAmount)
            binding.expense.text = "$ %.2f".format(expenseAmount)

        }

}