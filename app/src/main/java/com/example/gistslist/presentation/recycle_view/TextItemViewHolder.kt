package com.example.gistslist.presentation.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.models.data.gist.GistModel
import kotlinx.android.synthetic.main.text_item_view.view.*

class TextItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
	fun bind(gistModel: GistModel) {
		itemView.login_title.text = gistModel.userLogin
		itemView.description_title.text = gistModel.gistDescription
	}
}