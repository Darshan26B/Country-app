package com.example.countryapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.countryapp.databinding.ActivityCountryFlagsMainBinding

class CountryFlagsMainActivity : AppCompatActivity() {
    lateinit var binding: ActivityCountryFlagsMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCountryFlagsMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        var pos = intent.getIntExtra("pos", 0)
        var modal = MainActivity.CountryList[pos]


        Glide.with(this).load(modal.flags?.png).into(binding.flagsclick)
        binding.txtIndia.text = modal.name
        binding.txtCapital2.text = modal.capital
        binding.txtCountryCode.text = modal.callingCodes.toString()
        binding.txtAllArea.text = modal.area.toString()
        binding.txtLanguage.text = modal.languages?.get(0)?.name
        binding.txtPopulation.text = modal.population.toString()
        binding.txtTimezone.text = modal.timezones.toString()
        binding.txtCurrencies.text = modal.currencies?.get(0)?.name
    }
}