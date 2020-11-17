package com.example.gistslist.presentation.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.gist_info_item.view.*

class GistInfoViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

	fun bind(nameField: String, contentField: String?) {
		if (contentField == null || contentField == "") {
			itemView.visibility = View.GONE
			itemView.layoutParams = RecyclerView.LayoutParams(0,0)
		} else {
			itemView.gist_info_title.text = nameField
			itemView.gist_info_text.text = contentField
		}
	}
}