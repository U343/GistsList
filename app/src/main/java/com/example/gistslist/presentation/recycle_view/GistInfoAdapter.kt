package com.example.gistslist.presentation.recycle_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gistslist.R
import com.example.gistslist.models.presentation.gist_info.GistInfoFields

/**
 * Адаптер recycler view для полей информации о гисте
 *
 * @author Dmitrii Bondarev on 17.11.2020
 */
class GistInfoAdapter: RecyclerView.Adapter<GistInfoViewHolder>() {
	private var gistInfoList = emptyList<String?>()

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GistInfoViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)

		val view = layoutInflater
			.inflate(R.layout.gist_info_item, parent, false)

		return GistInfoViewHolder(view)
	}

	override fun onBindViewHolder(holder: GistInfoViewHolder, position: Int) {
		if (GistInfoFields.fieldsNameList.size == gistInfoList.size) {
			holder.bind(GistInfoFields.fieldsNameList[position], gistInfoList[position])
		}
	}

	override fun getItemCount(): Int = gistInfoList.size

	/**
	 * Установка списка параметров гиста
	 */
	fun setData(data: List<String?>) {
		gistInfoList = data
	}
}