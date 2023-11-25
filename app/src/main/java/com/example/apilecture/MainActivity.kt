package com.example.apilecture

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // create retrofitBuilder
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        // data received
        val retrofitData = retrofitBuilder.getProductData()

        // enqueue method
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // if api call is success, then use the data of API and show in your app

                val responseBody = response.body()
                val productList = responseBody?.products

                val collectDataInSB = StringBuffer()

                if (productList != null) {
                    for (myData in productList){
                        collectDataInSB.append(myData.title + "\n ")
                    }

                    val tv = findViewById<TextView>(R.id.textView)
                    tv.text = collectDataInSB

                }
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // if API call fails
                Log.d("MainActivity","OnFailure : " + t.message)
            }
        })

    }
}