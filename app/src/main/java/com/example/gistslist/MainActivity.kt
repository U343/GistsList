package com.example.gistslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
		observeLoadDataStatus()

		add_button.setOnClickListener {
			onClickAddButton()
		}
	}

	private fun onClickAddButton() {
		viewModel.loadGists()
	}


	private fun observeListForRecycleView() {
		val numberListObserver = Observer<ArrayList<String>> { numberList ->

			recyclerViewAdapter.setData(numberList)
			recycler_view.adapter?.notifyDataSetChanged()
		}
		viewModel.numberList.observe(this, numberListObserver)
	}

	private fun observeLoadDataStatus() {
		val loadStatus = Observer<Boolean> { isLoadGistSuccess ->
			if (isLoadGistSuccess == false) {
				showLoadingErrorToast(this)
			}
		}
		viewModel.isLoadGistSuccess.observe(this, loadStatus)
	}


	private fun initRecyclerView() {
		recyclerViewAdapter = ItemListAdapter()

		recycler_view.adapter = recyclerViewAdapter
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)
	}
}

