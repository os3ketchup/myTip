package com.eldorteshaboev.tiptime

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eldorteshaboev.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()

        }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfService.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost==null){
            binding.tipResult.text = ""
            return
        }
        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.twentyPercent -> 0.20
            R.id.eghTeenPercent -> 0.18
            else -> 0.15
        }
        var tip = cost * tipPercentage
        if (binding.roundUpSwitch.isChecked){
            tip = ceil(tip)
           val formattedTip = NumberFormat.getCurrencyInstance().format(tip)
            binding.tipResult.text = getString(R.string.tip_amount,formattedTip)
        }
    }
}