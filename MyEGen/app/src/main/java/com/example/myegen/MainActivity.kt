package com.example.myegen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.isVisible

import com.example.myegen.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.goinputbtn.setOnClickListener {
            val intent = Intent(this, InputActivity::class.java)
            intent.putExtra("intentMessage", "응급의료정보")
            startActivity(intent)
        }
        binding.dltbtn.setOnClickListener {
            deleteData()
        }
        binding.tellayer.setOnClickListener {
            with(Intent(Intent.ACTION_VIEW)){
                val phoneNumber = binding.telval.text.toString()
                    .replace("-","")
                data = Uri.parse("tel:$phoneNumber")
                startActivity(this)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        getData()

    }

    private fun getData(){
        with(getSharedPreferences(USER_INFORMATION,Context.MODE_PRIVATE)){
            binding.nameval.text = getString(NAME, "입력안함")
            binding.birthval.text = getString(BIRTH, "입력안함")
            binding.bldtypeval.text = getString(BLD, "입력안함")
            binding.telval.text = getString(TEL, "입력안함")
            val notice = getString(NOTICE,"입력안함")
            binding.notice.isVisible = notice.isNullOrEmpty().not()
            binding.noticeval.isVisible = notice.isNullOrEmpty().not()

            if(!notice.isNullOrEmpty()){
                binding.noticeval.text = notice
            }
        }
    }
    private fun deleteData(){
        with(getSharedPreferences(USER_INFORMATION, MODE_PRIVATE).edit()) {
            clear()
            apply()
            getData()
        }
        Toast.makeText(this, "초기화되었습니다.", Toast.LENGTH_SHORT).show()
    }
}