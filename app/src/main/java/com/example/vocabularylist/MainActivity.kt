package com.example.vocabularylist


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vocabularylist.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), WordAdapter.ItemClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var wordadapter: WordAdapter
    private var selectedWord: Word? = null
    private val updateAddwordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val isUpdated = result.data?.getBooleanExtra("isUpdated", false) ?: false

        if (result.resultCode == RESULT_OK && isUpdated) {
            if (isUpdated) {
                updateAddword()
            }
        }
    }
    private val updateEditwordResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        val editWord = result.data?.getParcelableExtra<Word>("editWord")
        if (result.resultCode == RESULT_OK && editWord != null) {
            updateEditword(editWord)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        binding.btnGotoAdd.setOnClickListener {
            Intent(this, AddActivity::class.java).let {
                updateAddwordResult.launch(it)
            }
        }
        binding.btnDelete.setOnClickListener {
            delete()
        }
        binding.btnEdit.setOnClickListener {
            edit()
        }
    }

    private fun initRecyclerView() {
        wordadapter = WordAdapter(mutableListOf(), this)
        binding.wordRecyclerView.apply {
            adapter = wordadapter
            layoutManager =
                LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration =
                DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            addItemDecoration(dividerItemDecoration)
        }
        Thread {
            val list = AppDatabase.getInstance(this)?.wordDao()?.getAll() ?: emptyList()
            wordadapter.list.addAll(list)
            runOnUiThread {
                wordadapter.notifyDataSetChanged()
            }
        }.start()
    }

    private fun updateAddword() {
        Thread {
            AppDatabase.getInstance(this)?.wordDao()?.getLatestWord()?.let { word ->
                wordadapter.list.add(0, word)
                runOnUiThread { wordadapter.notifyDataSetChanged() }
            }
        }.start()
    }

    private fun updateEditword(word: Word) {
        val index = wordadapter.list.indexOfFirst { it.id == word.id }
        wordadapter.list[index] = word
        runOnUiThread {
            selectedWord = word
            wordadapter.notifyItemChanged(index)
            binding.vocaTextView.text = word.voca
            binding.meanTextView.text = word.mean
        }
    }

    private fun delete() {
        if (selectedWord == null) return
        Thread {
            selectedWord?.let { word ->
                AppDatabase.getInstance(this)?.wordDao()?.delete(word)
                runOnUiThread {
                    wordadapter.list.remove(word)
                    wordadapter.notifyDataSetChanged()
                    binding.vocaTextView.text = ""
                    binding.meanTextView.text = ""
                    Toast.makeText(this, "삭제되었습니다.", Toast.LENGTH_SHORT).show()
                }
                selectedWord = null
            }
        }.start()
    }

    private fun edit() {
        if (selectedWord == null) return
        val intent = Intent(this, AddActivity::class.java).putExtra("originWord", selectedWord)
        updateEditwordResult.launch(intent)
    }

    override fun onClick(word: Word) {
        selectedWord = word
        binding.vocaTextView.text = word.voca
        binding.meanTextView.text = word.mean
    }
}