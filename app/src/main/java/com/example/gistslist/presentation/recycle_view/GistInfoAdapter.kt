package com.example.gistslist.presentation.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R

class GistInfoAdapter: RecyclerView.Adapter<GistInfoViewHolder>() {
	private var gistInfoList = emptyList<String>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistInfoViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)

		val view = layoutInflater
			.inflate(R.layout.gist_info_item, parent, false)

		return GistInfoViewHolder(view)
	}

	override fun onBindViewHolder(holder: GistInfoViewHolder, position: Int) {
		TODO("Not yet implemented")
	}

	override fun getItemCount(): Int = gistInfoList.size

	fun setData(data: List<String>) {
		gistInfoList = data
	}
}