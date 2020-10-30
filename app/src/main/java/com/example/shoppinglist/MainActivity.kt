package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.mainList.viewModel.MainListViewModel
import com.example.shoppinglist.mainList.isNumericString
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

	private fun observeListForRecycleView() {
		val numberListObserver = Observer<ArrayList<String>> { numberList ->
			recycler_view.adapter = ItemListAdapter(numberList)
			recycler_view.adapter?.notifyDataSetChanged()
		}

		viewModel.numberList.observe(this, numberListObserver)
	}

	private fun clickAddButton() {
		val inputData = getInputValue()
		Log.d("resGist", "click button")

		if (isDataValid(inputData)) {
			Log.d("resGist", "go to viewModel")
			viewModel.loadGists()
		} else {
			showInvalidInputTypeToast(this)
		}
	}

	private fun getInputValue(): String =
		main_edit_text.text.toString()

	private fun isDataValid(data: String): Boolean =
		 (isNumericString(data) && data.toInt() > 0)

	private fun initRecyclerView() {
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)
	}
}

