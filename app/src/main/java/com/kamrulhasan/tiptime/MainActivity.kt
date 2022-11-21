package com.kamrulhasan.tiptime

import android.annotation.SuppressLint
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.core.graphics.red
import com.kamrulhasan.tiptime.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.emptyFlow
import java.text.NumberFormat
import kotlin.math.cos
import kotlin.math.round

@SuppressLint("StaticFieldLeak")
public lateinit var binding: ActivityMainBinding
val TAG : String = "MainActivity"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //initialisation
        binding.tvResult.text = getString(R.string.text_result,"--")


       // binding.btnCalculate.setBackgroundResource(R.drawable.border_radius)

        // custom input visibility control
        binding.rbtnOther.setOnCheckedChangeListener{
                buttonView, isChecked ->
            if(isChecked){
                binding.etOtherRadiobutton.visibility = View.VISIBLE
            }
            else{
                binding.etOtherRadiobutton.visibility = View.GONE
                binding.etOtherRadiobutton.text = null
            }
        }
        // Calculate button activities
        binding.btnCalculate.setOnClickListener(View.OnClickListener {

            if(binding.etPrice.text.isEmpty()){
                binding.etPrice.setError("Required!")
                Log.e(TAG,"Cost amount must be fill up.")
            }
            else if (binding.etOtherRadiobutton.visibility == View.VISIBLE
                && binding.etOtherRadiobutton.text.isEmpty()){

                binding.etOtherRadiobutton.setError("Required")
                Log.e(TAG,"Input percentage must be fill up.")
            }
            else{
                tipCalculate()
            }

        })

        binding.btnReset.setOnClickListener(View.OnClickListener {
            reset()
        })
    }
    fun tipCalculate(){

        val tipOption = binding.rgOptionOfTip.checkedRadioButtonId
        val tipPercentage = when(tipOption){
            R.id.rbtn_amazing -> 0.2
            R.id.rbtn_good -> 0.18
            R.id.rbtn_ok -> 0.15
            else -> binding.etOtherRadiobutton.text
                .toString()
                .toDouble() / 100.0
        }

        var cost = binding.etPrice.text.toString().toDouble() * tipPercentage

        if (binding.swtRound.isChecked){
            cost = kotlin.math.ceil(cost)
            Log.d(TAG,"Round is checked.")
        }
        val tipCurrency = NumberFormat.getCurrencyInstance().format(cost)
        binding.tvResult.text = getString(R.string.text_result,tipCurrency)

    }

    //reset all values
    fun reset(){
        binding.etPrice.text = null
        binding.etOtherRadiobutton.text = null
        binding.rgOptionOfTip.check(R.id.rbtn_amazing)
        binding.tvResult.text = getString(R.string.text_result, "--")

    }

}