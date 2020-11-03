package com.example.gistslist.mainList.recycleView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R
import java.util.ArrayList

class ItemListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    private var data: ArrayList<String> = ArrayList()

    override fun getItemCount() = data.size

    init {
        Log.d("init", "Create recyclerView adapter")
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val currentItem = data[position]

        holder.textView.text = currentItem
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false) as TextView

        return TextItemViewHolder(view)
    }

    fun setData(data: ArrayList<String>) {
        this.data = data
    }
}