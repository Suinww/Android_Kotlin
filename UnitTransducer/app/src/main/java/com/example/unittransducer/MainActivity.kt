package com.example.unittransducer

import android.icu.text.Transliterator.Position
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import com.example.unittransducer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        var view = binding.root
        setContentView(view)
//나는 이제 뷰바인딩을 쓸꺼야

        var inputval = binding.inputval
        var outputval = binding.outputval
        var input = binding.input
        var output = binding.output
        var inputUnit: Spinner = binding.inputUnit
        var outputUnit: Spinner = binding.outputUnit
//이런 변수들을 가져와

        var inputNumber: Double = 0.0
//인풋넘버를 만들어

        var data =
            listOf<String>("선택", "mm", "cm", "m", "in", "ft", "yd", "mile")
//스피너에 이런 데이터를 넣을꺼야

        input.addTextChangedListener { text ->
            inputNumber = if (text.isNullOrEmpty()) {
                inputNumber
            } else text.toString().toDouble()
        }

        var inadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        inputUnit.adapter = inadapter
        inputUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var selectedinput = data[p2]
                inputval.text = selectedinput
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }

        var outadapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, data)
        outputUnit.adapter = outadapter
        outputUnit.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                var selectedoutput = data[p2]
                outputval.text = selectedoutput

                if (inputval.equals(outputval)) {
                    output.text = inputNumber.toString()
                }
                else {
                    if (inputval.text.toString().contentEquals("mm")) {
                        if (outputval.text.toString().contentEquals("cm")) {
                            val outputNumber = inputNumber.times(0.1)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("m")) {
                            val outputNumber = inputNumber.times(0.001)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("in")) {
                            val outputNumber = inputNumber.times(0.0393700787)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("ft")) {
                            val outputNumber = inputNumber.times(0.0032808399)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("yd")) {
                            val outputNumber = inputNumber.times(0.0010936133)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("mile")) {
                            val outputNumber = inputNumber.times(0.000000621371)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    } else if (inputval.text.toString().contentEquals("cm")) {
                        if (outputval.text.toString().contentEquals("mm")) {
                            val outputNumber = inputNumber.times(10)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("m")) {
                            val outputNumber = inputNumber.times(0.01)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("in")) {
                            val outputNumber = inputNumber.times(0.393701)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("ft")) {
                            val outputNumber = inputNumber.times(0.032808)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("yd")) {
                            val outputNumber = inputNumber.times(0.010936)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("mile")) {
                            val outputNumber = inputNumber.times(0.00000621371)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    } else if (inputval.text.toString().contentEquals("m")) {
                        if (outputval.text.toString().contentEquals("mm")) {
                            val outputNumber = inputNumber.times(1000)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("cm")) {
                            val outputNumber = inputNumber.times(100)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("in")) {
                            val outputNumber = inputNumber.times(39.37001)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("ft")) {
                            val outputNumber = inputNumber.times(3.28084)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("yd")) {
                            val outputNumber = inputNumber.times(1.093613)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("mile")) {
                            val outputNumber = inputNumber.times(0.000621371)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    } else if (inputval.text.toString().contentEquals("in")) {
                        if (outputval.text.toString().contentEquals("mm")) {
                            val outputNumber = inputNumber.times(25.4)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("cm")) {
                            val outputNumber = inputNumber.times(2.54)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("m")) {
                            val outputNumber = inputNumber.times(0.0254)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("ft")) {
                            val outputNumber = inputNumber.times(0.08333333333)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("yd")) {
                            val outputNumber = inputNumber.times(0.02777777778)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("mile")) {
                            val outputNumber = inputNumber.times(0.0000157828)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    } else if (inputval.text.toString().contentEquals("ft")) {
                        if (outputval.text.toString().contentEquals("mm")) {
                            val outputNumber = inputNumber.times(304.8)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("cm")) {
                            val outputNumber = inputNumber.times(30.48)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("m")) {
                            val outputNumber = inputNumber.times(0.3048)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("in")) {
                            val outputNumber = inputNumber.times(12)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("yd")) {
                            val outputNumber = inputNumber.times(0.333333333)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("mile")) {
                            val outputNumber = inputNumber.times(0.000189394)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    } else if (inputval.text.toString().contentEquals("yd")) {
                        if (outputval.text.toString().contentEquals("mm")) {
                            val outputNumber = inputNumber.times(914.4)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("cm")) {
                            val outputNumber = inputNumber.times(91.44)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("m")) {
                            val outputNumber = inputNumber.times(0.9144)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("in")) {
                            val outputNumber = inputNumber.times(36)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("ft")) {
                            val outputNumber = inputNumber.times(3)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("mile")) {
                            val outputNumber = inputNumber.times(0.000568182)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    } else if (inputval.text.toString().contentEquals("mile")) {
                        if (outputval.text.toString().contentEquals("mm")) {
                            val outputNumber = inputNumber.times(1609344)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("cm")) {
                            val outputNumber = inputNumber.times(160934.4)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("m")) {
                            val outputNumber = inputNumber.times(1609344)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("in")) {
                            val outputNumber = inputNumber.times(63360)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("ft")) {
                            val outputNumber = inputNumber.times(5280)
                            output.text = outputNumber.toString()
                        } else if (outputval.text.toString().contentEquals("yd")) {
                            val outputNumber = inputNumber.times(1760)
                            output.text = outputNumber.toString()
                        } else {
                            val outputNumber = inputNumber
                            output.text = outputNumber.toString()
                        }
                    }
                }
            }
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }
}
