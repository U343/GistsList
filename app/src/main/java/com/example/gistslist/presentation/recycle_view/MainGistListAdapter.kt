package com.example.gistslist.presentation.recycle_view

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R
import com.example.gistslist.models.presentation.gist_model.GistListModel

/**
 * Адаптер recycler view для списка гистов
 *
 * @param listener Слушатель нажатий для элемента списка
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistListAdapter(private val listener: (Any) -> Unit) :
	RecyclerView.Adapter<MainGistListViewHolder>() {

	private var data = emptyList<GistListModel>()

	override fun getItemCount() = data.size

	override fun onBindViewHolder(holder: MainGistListViewHolder, position: Int) {
		holder.bind(data[position])

		holder.itemView.setOnClickListener {
			listener(data[position].gistId)
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainGistListViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)

		val view = layoutInflater
			.inflate(R.layout.main_gist_list_item, parent, false)

		return MainGistListViewHolder(view)
	}

	/**
	 * Установка списка гистов
	 *
	 * @param data список гистов
	 */
	fun setData(data: List<GistListModel>) {
		this.data = data
		notifyDataSetChanged()
	}
}
