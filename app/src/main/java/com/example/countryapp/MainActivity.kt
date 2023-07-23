package com.example.countryapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.countryapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Locale

class MainActivity : AppCompatActivity() {

    companion object {
        var CountryList = ArrayList<CountryModel>()

    }

    private lateinit var adapter: CountryAdapter
    lateinit var binding: ActivityMainBinding
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         binding.edtSearch.setOnQueryTextListener(object :
             SearchView.OnQueryTextListener {
             override fun onQueryTextSubmit(query: String?): Boolean {
                 return false
             }

             override fun onQueryTextChange(newText: String?): Boolean {
                 filterList(newText)
                 return true
             }

             private fun filterList(query: String?) {

                 if (query != null) {
                     var filteredList = ArrayList<CountryModel>()
                     for (i in CountryList) {
                         if (i.name?.lowercase(Locale.ROOT)!!.contains(query)) {
                             filteredList.add(i)
                         }
                     }
                     if (filteredList.isEmpty()) {
                         Toast.makeText(
                             this@MainActivity,
                             "No Data found",
                             Toast.LENGTH_SHORT
                         ).show()
                     } else {
                         adapter.setfilteredList(filteredList)

                     }

                 }
             }

         })
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
                    var clickItem = object : CountryClick {
                        override fun onTap(i: Int) {
                            var intent = Intent(
                                this@MainActivity,
                                CountryFlagsMainActivity::class.java
                            ).putExtra("pos", i)
                            startActivity(intent)
                        }

                    }


                    binding.rcvflags.layoutManager = GridLayoutManager(this@MainActivity, 1)
                    binding.rcvflags.adapter = CountryAdapter(CountryList, clickItem)


                }

            }

            override fun onFailure(call: Call<List<CountryModel>>, t: Throwable) {

            }

        })

    }
}