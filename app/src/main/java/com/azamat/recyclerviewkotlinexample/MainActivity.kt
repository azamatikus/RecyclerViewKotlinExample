package com.azamat.recyclerviewkotlinexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.azamat.recyclerviewkotlinexample.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity(), ExampleAdapter.OnItemClicklistener {
    private val exampleList = generateDummyList(500)
    private val adapter = ExampleAdapter(exampleList,this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(getLayoutInflater())

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        setContentView(binding.root)
    }

    fun insertItem(view: View) {

        val index = Random.nextInt(8)
        val newItem = ExampleItem(
            R.drawable.ic_android,
            "new item at position $index",
            "Line2")

        exampleList.add(index,newItem)
        adapter.notifyItemInserted(index)
    }

    fun deleteItem(view: View) {
        val index = Random.nextInt(8)
        exampleList.removeAt(index)
        adapter.notifyItemRemoved(index)
    }

    private fun generateDummyList(size: Int): ArrayList<ExampleItem> {

        val list = ArrayList<ExampleItem>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.ic_android
                1 -> R.drawable.ic_audio
                else -> R.drawable.ic_sun
            }

            val item = ExampleItem(drawable, "Item $i", "Line 2")
            list += item
        }

        return list
    }

    override fun onItemClick(position: Int) {

        Toast.makeText(this, "item $position clicked", Toast.LENGTH_SHORT).show()
        val clickedItem = exampleList[position]
        clickedItem.Text1 = "clicked"
        adapter.notifyItemChanged(position)
    }


}