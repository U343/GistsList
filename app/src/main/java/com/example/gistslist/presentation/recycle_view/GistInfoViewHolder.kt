package com.example.gistslist.presentation.recycle_view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.gist_info_item.view.*

/**
 * Холдер recycler view для списка информации о гисте
 *
 * @param itemView Элемент с информацией о гисте
 *
 * @author Dmitrii Bondarev on 17.11.2020
 */
class GistInfoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

	/**
	 * Устанавливает текстовые значения для полей вью элемента
	 *
	 * Если характиристика гиста отсутствует, то элемент скрывается в представлении
	 *
	 * @param [nameField] Название характеристики гиста
	 * @param [contentField] Характеристика гиста
	 */
	//TODO Уверен что здесь плохо делаю, но ничего лучше не придумал. На этом экране я вывожу список
	// с инфой о гисте. Уменя есть список с названиями характеристик и список с самими характеристиками
	// Мне очень не нравится что эти списки вроде никак не зависят друг от друга и если придется добавлять
	// новые параметыр то придется менять два списка да и еще за их порядком следить
	fun bind(nameField: String, contentField: String?) {
		if (contentField == null || contentField == "") {
			itemView.visibility = View.GONE
			itemView.layoutParams = RecyclerView.LayoutParams(0, 0)
		} else {
			itemView.gist_info_title.text = nameField
			itemView.gist_info_text.text = contentField
		}
	}
}