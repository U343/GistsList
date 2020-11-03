package com.example.gistslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.gistModel.GistObject
import com.example.gistslist.mainList.viewModel.MainListViewModel
import com.example.gistslist.mainList.recycleView.ItemListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainListViewModel
	private lateinit var recyclerViewAdapter: ItemListAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)

		initRecyclerView()
		observeListForRecycleView()

		add_button.setOnClickListener {
			viewModel.getGistsList()
			runProgressBar()
		}
	}

	private fun observeListForRecycleView() {
		val numberListObserver = Observer<ArrayList<GistObject>> { gistsList ->
// TODO тут я решил проблему с пересаздающимся адаптером, добавив ему метод setData. Мне способ нравится, но не уверен что это прям то что нужно
			recyclerViewAdapter.setData(gistsList)
			recycler_view.adapter?.notifyDataSetChanged()
			stopProgressBar()
		}
		viewModel.gistsStringList.observe(this, numberListObserver)
	}

	private fun initRecyclerView() {
		recyclerViewAdapter = ItemListAdapter()

		recycler_view.adapter = recyclerViewAdapter
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)
	}
//TODO очень простая реализация прогресс бара получилась, это нормально?
	private fun runProgressBar() {
		progress_bar.visibility = View.VISIBLE
	}

	private fun stopProgressBar() {
		progress_bar.visibility = View.INVISIBLE
	}
}

