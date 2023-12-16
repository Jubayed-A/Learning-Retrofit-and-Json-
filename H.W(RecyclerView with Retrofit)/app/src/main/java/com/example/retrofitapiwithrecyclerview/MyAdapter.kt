package com.example.retrofitapiwithrecyclerview

import android.app.Activity
import android.view.LayoutInflater
import android.view.OnReceiveContentListener
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private val context: Activity, private val productArrayList: List<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    private lateinit var myListener: onItemClickLister

    interface onItemClickLister {
        fun onItemClicking(position: Int)
    }

    fun setOnItemClickListener(lister: onItemClickLister) {
        myListener = lister
    }

    class MyViewHolder(itemView: View, lister: onItemClickLister) :
        RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var reating: RatingBar
        var image: ShapeableImageView

        init {
            title = itemView.findViewById(R.id.productTitle)
            reating = itemView.findViewById(R.id.ratingBar)
            image = itemView.findViewById(R.id.shapeableImageView)
            itemView.setOnClickListener {
                lister.onItemClicking(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.custom_item, parent, false)
        return MyViewHolder(itemView, myListener)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.title.text = currentItem.title
        holder.reating.rating = currentItem.rating.toFloat()
        // image show from json file
        Picasso.get().load(currentItem.thumbnail).into(holder.image)
    }
}