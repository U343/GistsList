package com.example.gistslist.presentation.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.models.presentation.gist_model.GistModel
import kotlinx.android.synthetic.main.text_item_view.view.*

/**
 * Холдер recycler view для списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class TextItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	/**
	 * Установка текстовых параметров для view элемента
	 *
	 * @param gistModel объект с информацией о гисте
	 */
	fun bind(gistModel: GistModel) {
		itemView.login_title.text = gistModel.userLogin
		itemView.description_title.text = gistModel.gistDescription
	}
}