package com.example.gistslist.presentation.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R
import com.example.gistslist.models.data.gist.GistModel

class ItemListAdapter : RecyclerView.Adapter<TextItemViewHolder>() {
	private var data = emptyList<GistModel>()

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: TextItemViewHolder, position: Int) {
		holder.bind(data[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextItemViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)

		val view = layoutInflater
			.inflate(R.layout.text_item_view, parent, false)

		return TextItemViewHolder(view)
	}

	fun setData(data: List<GistModel>) {
		this.data = data
		notifyDataSetChanged()
	}
}