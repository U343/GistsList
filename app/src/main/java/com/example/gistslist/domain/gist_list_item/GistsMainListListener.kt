package com.example.gistslist.domain.gist_list_item

/**
 * Слушатель нажантий recycler view с гистами
 *
 * @author Dmitrii Bondarev on 12.11.2020
 */
interface GistsMainListListener {
	/**
	 * Слушатель нажатий для элементов списка с гистами
	 *
	 * @param position Позиция нажатого элемента списка
	 */
	fun onItemClick(position: Int)
}