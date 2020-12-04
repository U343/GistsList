package com.example.gistslist.presentation.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.models.presentation.gist_model.GistListModel
import kotlinx.android.synthetic.main.main_gist_list_item.view.*

/**
 * Холдер recycler view для списка гистов
 *
 * @param itemView Элемент списка с гистами
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	/**
	 * Установка текстовых параметров для view элемента
	 *
	 * @param gistModel объект с информацией о гисте
	 */
	fun bind(model: GistListModel) {
		itemView.login_title.text = model.gistName
		itemView.description_title.text = model.gistDescription
	}
}