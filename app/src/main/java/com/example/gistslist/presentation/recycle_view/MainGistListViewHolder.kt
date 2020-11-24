package com.example.gistslist.presentation.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.models.presentation.gist_model.GistListModel
import kotlinx.android.synthetic.main.main_gist_list_item.view.*

/**
 * Холдер recycler view для списка гистов
 *
 * @param itemView Элемент списка с гистами
 * @param listener Слушатель нажатий для элемента списка
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistListViewHolder(
	itemView: View,
	private val data: List<GistListModel>,
	private val listener: (Any) -> Unit
) :
	RecyclerView.ViewHolder(itemView), View.OnClickListener {

	init {
		itemView.setOnClickListener(this)
	}

	/**
	 * Установка текстовых параметров для view элемента
	 *
	 * @param gistModel объект с информацией о гисте
	 */
	fun bind(position: Int) {
		itemView.login_title.text = data[position].gistName
		itemView.description_title.text = data[position].gistDescription
	}

	override fun onClick(v: View?) {
		val position = adapterPosition
		listener(data[position].gistId)
	}
}