package com.example.stopwatch

import android.media.AudioManager
import android.media.ToneGenerator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import com.example.stopwatch.databinding.ActivityMainBinding
import com.example.stopwatch.databinding.DialogCountdownSettingBinding
import java.util.*
import kotlin.concurrent.timer

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var countdownSeconds = 0
    private var currntCountdownDeciSeconds = countdownSeconds * 100
    private var currentDeciSeconds = 0
    private var timer: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.countdownTextView.setOnClickListener {
            showCountdownSettingDialog()
        }

        binding.btnStart.setOnClickListener {
            start()
            binding.btnStart.isVisible = false
            binding.btnStop.isVisible = false
            binding.btnPause.isVisible = true
            binding.btnLap.isVisible = true
        }
        binding.btnStop.setOnClickListener {
            showAletDialog()

        }
        binding.btnPause.setOnClickListener {
            pause()
            binding.btnStart.isVisible = true
            binding.btnStop.isVisible = true
            binding.btnPause.isVisible = false
            binding.btnLap.isVisible = false
        }
        binding.btnLap.setOnClickListener {
            lap()
        }
        initCountdownViews()
    }
    private fun initCountdownViews(){
        binding.countdownTextView.text = String.format("%02d", countdownSeconds)
        binding.countdownProgressBar.progress = 100
    }

    private fun start() {
        timer = timer(initialDelay = 0, period = 10) {
            if (currntCountdownDeciSeconds == 0) {
                currentDeciSeconds += 1

                var min = currentDeciSeconds.div(100) / 60
                var sec = currentDeciSeconds.div(100) % 60
                var deciSec = currentDeciSeconds % 100

                runOnUiThread {
                    binding.timeTextView.text =
                        String.format("%02d:%02d", min, sec)
                    binding.tickTextView.text = String.format("%02d", deciSec)

                    binding.groupCountdown.isVisible = false
                }
            } else {
                currntCountdownDeciSeconds -= 1
                val seconds = currntCountdownDeciSeconds / 100
                var progress = currntCountdownDeciSeconds / (countdownSeconds * 1f)

                binding.root.post {
                    binding.countdownTextView.text = String.format("%02d", seconds)
                    binding.countdownProgressBar.progress = progress.toInt()
                }

            }
            if(currentDeciSeconds == 0 && currntCountdownDeciSeconds < 301
                && currntCountdownDeciSeconds % 100 == 0){
                val tonetype = if(currntCountdownDeciSeconds == 0)
                    ToneGenerator.TONE_CDMA_HIGH_L else ToneGenerator.TONE_CDMA_ANSWER


                ToneGenerator(AudioManager.STREAM_ALARM, ToneGenerator.MAX_VOLUME)
                    .startTone(tonetype, 100)
            }
        }
    }

    private fun stop() {
        binding.btnStart.isVisible = true
        binding.btnStop.isVisible = true
        binding.btnPause.isVisible = false
        binding.btnLap.isVisible = false

        currentDeciSeconds = 0
        currntCountdownDeciSeconds = countdownSeconds * 100
        binding.timeTextView.text = "00:00"
        binding.tickTextView.text = "00"

        binding.groupCountdown.isVisible = true
        initCountdownViews()
        binding.labContainer.removeAllViews()
    }

    private fun pause() {
        timer?.cancel()
        timer = null
    }

    private fun lap() {
        if(currentDeciSeconds == 0)return
        var container = binding.labContainer
        TextView(this).apply{
            textSize = 20f
            gravity = Gravity.CENTER
            var min = currentDeciSeconds.div(100) / 60
            var sec = currentDeciSeconds.div(100) % 60
            var deciSec = currentDeciSeconds % 100
            text = container.childCount.inc().toString() + ") " + String.format(
                "%02d:%02d %02d" , min,sec,deciSec
            )
            setPadding(30)
        }.let{ labTextView ->
            container.addView(labTextView,0)
        }

    }

    private fun showCountdownSettingDialog() {
        AlertDialog.Builder(this).apply {
            val dialogbinding = DialogCountdownSettingBinding.inflate(layoutInflater)
            with(dialogbinding.countdownSecondPicker) {
                maxValue = 20
                minValue = 0
                value = countdownSeconds
            }
            setTitle("카운트다운 설정")
            setView(dialogbinding.root)
            setPositiveButton("확인") { _, _ ->
                countdownSeconds = dialogbinding.countdownSecondPicker.value
                currntCountdownDeciSeconds = countdownSeconds * 100
                binding.countdownTextView.text = String.format("%02d", countdownSeconds)
            }
            setNegativeButton("취소", null)
        }.show()
    }

    private fun showAletDialog() {
        AlertDialog.Builder(this).apply {
            setMessage("종료하시겠습니까?")
            setPositiveButton("네") { _, _ ->
                stop()
            }
            setNegativeButton("아니요", null)
        }.show()
    }
}