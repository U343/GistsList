package com.example.gistslist.presentation.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R
import com.example.gistslist.models.presentation.gist_model.GistModel

/**
 * Адаптер recycler view для списка гистов
 *
 * @param listener Слушатель нажатий для элемента списка
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistListAdapter(private val listener: (Any) -> Unit) :
	RecyclerView.Adapter<MainGistListViewHolder>() {

	private var data = emptyList<GistModel>()

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: MainGistListViewHolder, position: Int) {
		holder.bind(data[position])
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainGistListViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)

		val view = layoutInflater
			.inflate(R.layout.main_gist_list_item, parent, false)

		return MainGistListViewHolder(view, listener)
	}

	/**
	 * Установка списка гистов
	 *
	 * @param data список гистов
	 */
	fun setData(data: List<GistModel>) {
		this.data = data
		notifyDataSetChanged()
	}
}