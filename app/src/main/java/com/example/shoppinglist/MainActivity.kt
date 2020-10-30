package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.mainList.viewModel.MainListViewModel
import com.example.shoppinglist.mainList.recycleView.ItemListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainListViewModel


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)

		initRecyclerView()
		observeListForRecycleView()

		add_button.setOnClickListener {
			clickAddButton()
		}
	}
//TODO красиво ли объявление вьюшек и их инициальзацию оставлять в MainActivity? Может это стоит перекинуть в другой класс?
	private fun observeListForRecycleView() {
		val numberListObserver = Observer<ArrayList<String>> { numberList ->
			recycler_view.adapter = ItemListAdapter(numberList)
			recycler_view.adapter?.notifyDataSetChanged()
		}

		viewModel.numberList.observe(this, numberListObserver)
	}

	private fun clickAddButton() {
			viewModel.loadGists()
	}

	private fun initRecyclerView() {
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)
	}
}

