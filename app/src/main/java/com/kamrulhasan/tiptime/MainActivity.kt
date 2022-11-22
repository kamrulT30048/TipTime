package com.kamrulhasan.tiptime


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.kamrulhasan.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tag: String = "MainActivity"
//    private val mgs: String = "logcat"

    // @Activity onCreate Function
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Initialisation binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initialisation
        binding.tvResult.text = getString(R.string.text_result, "--")
        // custom input visibility control
        binding.rbtnOther.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.etOtherRadiobutton.visibility = View.VISIBLE
            } else {
                binding.etOtherRadiobutton.visibility = View.GONE
                binding.etOtherRadiobutton.text = null
            }
        }
        // Calculate button activities
        binding.btnCalculate.setOnClickListener {
            if (binding.etPrice.text.isEmpty()) {
                binding.etPrice.error = "Required!"
                Log.e(tag, "Cost amount must be fill up.")
            } else if (binding.etOtherRadiobutton.visibility == View.VISIBLE
                && binding.etOtherRadiobutton.text.isEmpty()
            ) {
                binding.etOtherRadiobutton.error = "Required"
                Log.e(tag, "Input percentage must be fill up.")
            } else {
                tipCalculate()
            }
        }
        binding.btnReset.setOnClickListener {
            reset()
        }
    }

    // calculation function
    private fun tipCalculate() {
        //cause for crash the app
        //binding.etPrice.text = null
        val tipPercentage = when (binding.rgOptionOfTip.checkedRadioButtonId) {
            R.id.rbtn_amazing -> 0.2
            R.id.rbtn_good -> 0.18
            R.id.rbtn_ok -> 0.15
            else -> binding.etOtherRadiobutton.text
                .toString()
                .toDouble() / 100.0
        }
        var cost = binding.etPrice.text.toString().toDouble() * tipPercentage
        if (binding.swtRound.isChecked) {
            cost = ceil(cost)
            Log.d(tag, "Round is checked.")
        }
        val tipCurrency = NumberFormat.getCurrencyInstance().format(cost)
        binding.tvResult.text = getString(R.string.text_result, tipCurrency)
    }

    //reset all values
    private fun reset() {
        binding.etPrice.text = null
//        binding.etPrice.error = View.VISIBLE
        binding.etOtherRadiobutton.text = null
        binding.rgOptionOfTip.check(R.id.rbtn_amazing)
        binding.tvResult.text = getString(R.string.text_result, "--")
        binding.swtRound.isChecked = true
    }
}