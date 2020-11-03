package com.example.gistslist.mainList.recycleView

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R
import com.example.gistslist.gistModel.GistObject
import kotlinx.android.synthetic.main.text_item_view.view.*
import java.util.ArrayList

class ItemListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
    private var data: ArrayList<GistObject> = ArrayList()

    override fun getItemCount() = data.size

    init {
        Log.d("init", "Create recyclerView adapter")
    }

    override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
        val currentItem = data[position]
//TODO вопрос по правилам чистого кода, мне кажется не особо хорошо что я тут взаимодеюствую с элементами активити, может это куда то перенести?
        holder.elemView.login_title.text = currentItem.userLogin
        holder.elemView.description_title.text = currentItem.gistDescription
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val view = layoutInflater
            .inflate(R.layout.text_item_view, parent, false)

        return TextItemViewHolder(view)
    }

    fun setData(data: ArrayList<GistObject>) {
        this.data = data
    }
}