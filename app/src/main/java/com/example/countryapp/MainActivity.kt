package com.example.countryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.countryapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    companion object {
        var CountryList = ArrayList<CountryModel>()

    }

    lateinit var binding: ActivityMainBinding
    lateinit var adapter: CountryAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        CallApi()

     }

    private fun CallApi() {
        var api = countryClient.getApiClient().create(Country::class.java)
        api.getCountrty().enqueue(object : Callback<List<CountryModel>> {
            override fun onResponse(
                call: Call<List<CountryModel>>,
                response: Response<List<CountryModel>>
            ) {
                    CountryList = response.body() as ArrayList<CountryModel>

                if (response.isSuccessful) {
                var clickItem = object  :CountryClick {
                    override fun onTap(i: Int) {
                        var intent = Intent(this@MainActivity,CountryFlagsMainActivity ::class.java).putExtra("pos", i)
                        startActivity(intent)
                    }

                }



                    binding.rcvflags.layoutManager = GridLayoutManager(this@MainActivity, 1)
                    binding.rcvflags.adapter = CountryAdapter(CountryList,clickItem)

                }

            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {

            }

        })

    }
}