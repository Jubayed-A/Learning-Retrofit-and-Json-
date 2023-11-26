package com.example.retrofitapiwithrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView

class ProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val heading = intent.getStringExtra("heading")
        val productDescription = intent.getStringExtra("description")
        val headingImg = intent.getIntExtra("image", R.drawable.img1)
        val productRating = intent.getFloatExtra("rating", 4.5f)

        val title = findViewById<TextView>(R.id.productHeading)
        val description = findViewById<TextView>(R.id.productDescription)
        val image = findViewById<ImageView>(R.id.productImg)
        val rating = findViewById<RatingBar>(R.id.ratingBar2)

        title.text = heading
        description.text = productDescription
        image.setImageResource(headingImg)
        rating.rating = productRating

    }
}