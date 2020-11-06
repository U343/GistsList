package com.example.gistslist.mainList.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.gistModel.GistObject
import com.example.gistslist.mainList.recycleView.ItemListAdapter
import com.example.gistslist.mainList.viewModel.MainListViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainGistsListFragment : Fragment() {
	private lateinit var viewModel: MainListViewModel
	private lateinit var recyclerViewAdapter: ItemListAdapter

	companion object {
		fun newInstance(): MainGistsListFragment {
			return MainGistsListFragment()
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.activity_main, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)

		initRecyclerView()
		observeListForRecycleView()

		add_button.setOnClickListener {
			viewModel.getGistsList()
			showProgressBar(true)
		}
	}

	private fun observeListForRecycleView() {
		val numberListObserver = Observer<ArrayList<GistObject>> { gistsList ->
// TODO тут я решил проблему с пересаздающимся адаптером, добавив ему метод setData. Мне способ нравится, но не уверен что это прям то что нужно
			recyclerViewAdapter.setData(gistsList)
			recycler_view.adapter?.notifyDataSetChanged()
			showProgressBar(false)
		}
		viewModel.gistsStringList.observe(this, numberListObserver)
	}

	private fun initRecyclerView() {
		recyclerViewAdapter = ItemListAdapter()

		recycler_view.adapter = recyclerViewAdapter
		recycler_view.layoutManager = LinearLayoutManager(activity)
		recycler_view.setHasFixedSize(true)
	}
	//TODO очень простая реализация прогресс бара получилась, это нормально?
	private fun showProgressBar(enabled: Boolean) {
		progress_bar.visibility = if (enabled) View.VISIBLE else View.INVISIBLE
	}
}