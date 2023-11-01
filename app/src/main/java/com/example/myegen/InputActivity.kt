package com.example.myegen

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.view.isVisible
import com.example.myegen.databinding.ActivityInputBinding

class InputActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInputBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInputBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bldspn.adapter = ArrayAdapter.createFromResource(
            this, R.array.bldarray,
            android.R.layout.simple_list_item_1
        )
        binding.birthlayer.setOnClickListener {
            val listener = OnDateSetListener { _, year, month, dayOfMonth ->
                binding.birthvalue.text = "$year-${month.inc()}-$dayOfMonth"
            }
            DatePickerDialog(
                this, listener,
                2020, 1, 1
            ).show()
        }
        binding.checkbox.setOnCheckedChangeListener { _, isChecked ->
            binding.noticevalue.isVisible = isChecked
        }
        binding.noticevalue.isVisible = binding.checkbox.isChecked
        binding.savebtn.setOnClickListener {
            saveData()
            finish()

        }
    }

    private fun saveData() {
        with(getSharedPreferences(USER_INFORMATION, Context.MODE_PRIVATE).edit()) {
            if (binding.namevalue.text.isNullOrEmpty()) {
                putString(NAME, "입력안함")
            } else {
                putString(NAME, binding.namevalue.text.toString())
            }

            if (binding.birthvalue.text.isNullOrEmpty()) {
                putString(BIRTH, "입력안함")
            } else {
                putString(BIRTH, binding.birthvalue.text.toString())
            }

            if (binding.bldspn.selectedItem.toString().contentEquals("혈액형")) {
                putString(BLD, "입력안함")
            } else {
                putString(BLD, getBld())
            }

            if (binding.telvalue.text.isNullOrEmpty()) {
                putString(TEL, "입력안함")
            } else {
                putString(TEL, binding.telvalue.text.toString())
            }
            if (binding.noticevalue.text.isNullOrEmpty()) {
                putString(NOTICE, "입력안함")
            } else {
                putString(NOTICE, getnotice())
            }





            apply()
        }
        Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show()
    }

    private fun getBld(): String {
        val bloodA = binding.bldspn.selectedItem.toString()
        val bloodpm = if (binding.bldPlus.isChecked) "RH+" else "RH-"
        return "$bloodpm$bloodA"
    }

    private fun getnotice(): String {
        return if (binding.checkbox.isChecked) binding.noticevalue.text.toString() else ""
    }

}