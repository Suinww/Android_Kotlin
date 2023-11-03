package com.example.newcal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.newcal.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val first = StringBuilder("")
    private val second = StringBuilder("")
    private val operator = StringBuilder("")
    private val percent = StringBuilder("")
    private val decimalFormat = DecimalFormat("#,###.######")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun numberClicked(view: View) {
        val numberString = (view as? Button)?.text?.toString() ?: ""
        val numberText = if (operator.isEmpty()) first else second

        numberText.append(numberString)
        updateEquation()
    }

    fun operatorClicked(view: View) {
        val operatorString = (view as? Button)?.text?.toString() ?: ""

        if (first.isEmpty()) {
            Toast.makeText(this, "숫자를 입력하세요", Toast.LENGTH_SHORT).show()
            return
        }
        operator.append(operatorString)
        updateEquation()
    }

    fun equalClicked(view: View) {
        if(binding.resultval.text.toString() == "0"){
        val firstNumber = first.toString().toDouble()
        val secondNumber = second.toString().toDouble()
        val result = when (operator.toString()) {
            "+" -> decimalFormat.format(firstNumber.plus(secondNumber))
            "-" -> decimalFormat.format(firstNumber.minus(secondNumber))
            "×" -> decimalFormat.format(firstNumber.times(secondNumber))
            "÷" -> decimalFormat.format(firstNumber.div(secondNumber))
            else -> "Error!"
        }.toString()
        binding.resultval.text = result
        }else{
            val firstNumber = binding.resultval.text.toString().toDouble()
            val secondNumber = second.toString().toDouble()
            val result = when (operator.toString()) {
                "+" -> decimalFormat.format(firstNumber.plus(secondNumber))
                "-" -> decimalFormat.format(firstNumber.minus(secondNumber))
                "×" -> decimalFormat.format(firstNumber.times(secondNumber))
                "÷" -> decimalFormat.format(firstNumber.div(secondNumber))
                else -> "Error!"
            }.toString()
            binding.resultval.text = result
        }
    }

    fun clearClicked(view: View) {
        percent.clear()
        second.clear()
        operator.clear()
        first.clear()

        updateEquation()
        binding.resultval.text = "0"
    }

    fun percentClicked(view: View) {
        val percentString = (view as? Button)?.text?.toString() ?: ""

        if (first.isEmpty() || second.isEmpty() || operator.isEmpty()) {
            return
        } else {
            val firstNumber = first.toString().toDouble()
            val secondNumber = second.toString().toDouble()
            val pctsnd = secondNumber / 100
            val resultpct = when (operator.toString()) {
                "+" -> decimalFormat.format(firstNumber.plus((firstNumber).times(pctsnd)))
                "-" -> decimalFormat.format(firstNumber.minus((firstNumber).times(pctsnd)))
                "×" -> decimalFormat.format(firstNumber.times(pctsnd))
                "÷" -> decimalFormat.format(firstNumber.div(pctsnd))
                else -> "Error!"
            }.toString()
            binding.resultval.text = resultpct
        }
        percent.append(percentString)
        updateEquation()
    }

    private fun updateEquation() {
        val firstfmtNumber = if (first.isNotEmpty())
            decimalFormat.format(first.toString().toDouble()) else ""
        val secondfmtNumber = if (second.isNotEmpty())
            decimalFormat.format(second.toString().toDouble()) else ""
        binding.equation.text = "$firstfmtNumber $operator $secondfmtNumber$percent"
    }
}