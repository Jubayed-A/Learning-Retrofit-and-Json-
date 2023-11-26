package com.example.retrofitapiwithrecyclerview

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var myAdapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)

        //
        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

        //
        val retrofitData = retrofitBuilder.getProductData()

        //
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                val responseBody = response.body()
                val productList = responseBody?.products!! // !! this mean is not empty

                myAdapter = MyAdapter(this@MainActivity, productList)
                recyclerView.adapter = myAdapter

                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)

                myAdapter.setOnItemClickListener(object : MyAdapter.onItemClickLister {
                    override fun onItemClicking(position: Int) {
                        val intentProduct = Intent(this@MainActivity, ProductActivity::class.java)
                        intentProduct.putExtra("heading", productList[position].title)
                        intentProduct.putExtra("description", productList[position].description)
                        intentProduct.putExtra("rating", productList[position].rating)
                        intentProduct.putExtra("image", productList[position].thumbnail)
                        startActivity(intentProduct)
                    }

                })
            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                Log.d("MainActivity", "OnFailure : " + t.message)
            }
        })

    }
}