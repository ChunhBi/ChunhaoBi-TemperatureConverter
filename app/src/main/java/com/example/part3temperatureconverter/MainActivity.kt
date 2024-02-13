package com.example.part3temperatureconverter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.SeekBar
import com.example.part3temperatureconverter.databinding.ActivityMainBinding

const val MAX_CELCIUS = 100f
const val MIN_CELCIUS = 0
const val MAX_FAHRENHEIT = 212f
const val MIN_FAHRENHEIT = 0
const val COLD_CELCIUS = 20
const val HOT_CELCIUS = 80

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.celciusBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the TextView with the current SeekBar value
                val value:Float = (MAX_CELCIUS-MIN_CELCIUS)*progress/100f
                setViewWithCelcius(value,progress)
                reactWithCelcius(fahrenheitToCelcius(value))
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
        binding.fahrenheitBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Update the TextView with the current SeekBar value
                val value:Float = (MAX_FAHRENHEIT- MIN_FAHRENHEIT)*progress/100f
                setViewWithCelcius(fahrenheitToCelcius(value),progress)
                reactWithCelcius(fahrenheitToCelcius(value))
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }
            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })
//        val textWatcher1 = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                try {
//                    val value = s.toString().toFloat()
//                    if (value < MIN_CELCIUS || value > MAX_CELCIUS) {
//                        binding.interestingMsg.setText("Celcius range: 0 - 100")
//                        return
//                    }
//                    val progress = (value * 100 / (MAX_CELCIUS-MIN_CELCIUS)).toInt()
//                    binding.celciusValue.removeTextChangedListener(this)
//                    setViewWithCelcius(value, progress)
//                    binding.celciusValue.addTextChangedListener(this)
//                }
//                catch (e:Exception){
//                    binding.interestingMsg.setText("Invalid input!")
//                }
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//        }
//        binding.celciusValue.addTextChangedListener (textWatcher1)
//        val textWatcher2 = object : TextWatcher {
//            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
//            }
//            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                try {
//                    val value = s.toString().toFloat()
//                    if (value < MIN_FAHRENHEIT || value > MAX_FAHRENHEIT) {
//                        binding.interestingMsg.setText("Fahrenheit range: 0 - 100")
//                        return
//                    }
//                    val progress = (value * 100 / (MAX_FAHRENHEIT-MIN_FAHRENHEIT)).toInt()
//                    binding.fahrenheitValue.removeTextChangedListener(this)
//                    setViewWithCelcius(fahrenheitToCelcius(value), progress)
//                    binding.fahrenheitValue.addTextChangedListener(this)
//                }
//                catch (e:Exception){
//                    binding.interestingMsg.setText("Invalid input!")
//                }
//            }
//            override fun afterTextChanged(s: Editable?) {
//            }
//        }
//        binding.fahrenheitValue.addTextChangedListener (textWatcher2)
    }

    private fun setViewWithCelcius(value:Float, progress:Int) {
        binding.celciusValue.setText(value.toString())
        binding.fahrenheitValue.setText(celciusToFahrenheit(value).toString())
        binding.celciusBar.setProgress(progress)
        binding.fahrenheitBar.setProgress(progress)
    }
    private fun reactWithCelcius(value:Float) {
        if (value <= COLD_CELCIUS) {
            binding.interestingMsg.setText("I wish it were warmer")
        }
        else if (value >= HOT_CELCIUS) {
            binding.interestingMsg.setText("I wish it were colder")
        }
    }
    private fun celciusToFahrenheit(value:Float): Float {
        return value*1.8f+32
    }
    private fun fahrenheitToCelcius(value:Float): Float {
        return (value-32)/1.8f
    }
}