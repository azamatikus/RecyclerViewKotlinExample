package com.azamat.recyclerviewkotlinexample

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(private val exampleList: List<ExampleItem>, private val listener:OnItemClicklistener) :
    RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.example_item, parent, false)
        return ExampleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = exampleList[position]

        holder.imageView.setImageResource(currentItem.ImageResourse)
        holder.textView1.text = currentItem.Text1
        holder.textView2.text = currentItem.Text2

//        if (position == 0){
//            holder.textView1.setBackgroundColor(Color.YELLOW)
//        }else{
//            holder.textView1.setBackgroundColor(Color.TRANSPARENT)
//        }
    }

    override fun getItemCount() = exampleList.size

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        val imageView: ImageView = itemView.findViewById(R.id.image_view)
        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val textView2: TextView = itemView.findViewById(R.id.textView2)

        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {

            val position:Int = absoluteAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position)
            }
        }

    }

    interface OnItemClicklistener{
        fun onItemClick(position:Int)
    }
}