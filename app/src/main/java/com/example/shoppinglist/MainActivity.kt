package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.mainList.MainListViewModel
import com.example.shoppinglist.mainList.recycleView.ItemListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainListViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)

		//viewModel.generateNumberList(5)
		if (viewModel.numberList != null) {
			callRecyclerView(viewModel.numberList!!)
		}


		//add_button.setOnClickListener()

		//val adapter = ShoppingListAdapter()
	}

	private fun getInputValueAndCheck() : Int {
		var inputValueToInt : Int
		val inputValue = main_edit_text.text.toString()

		try {
			inputValueToInt = inputValue.toInt()
		} catch (e: NumberFormatException) {
			return -1
		}
		if (inputValueToInt < 0) {
			inputValueToInt = -2
		}
		return inputValueToInt
	}

	private fun callRecyclerView(numberList: List<String>) {
		recycler_view.adapter = ItemListAdapter(numberList)
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)
	}

}