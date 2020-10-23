package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.mainList.MainListViewModel
import com.example.shoppinglist.mainList.isNumericString
import com.example.shoppinglist.mainList.isPositiveNumber
import com.example.shoppinglist.mainList.recycleView.ItemListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainListViewModel

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)

		if (viewModel.isNumberListExist()) {
			callRecyclerView(viewModel.numberList!!)
		}

		add_button.setOnClickListener {
			val inputData = getInputValue()
			if (isDataValid(inputData)) {
				viewModel.generateNumberList(inputData.toInt())
				callRecyclerView(viewModel.numberList!!)
			} else {
				showInvalidInputTypeToast(this)
			}
		}
	}

	private fun getInputValue() : String {
		return main_edit_text.text.toString()
	}

	private fun isDataValid(data : String) : Boolean {
		if (isNumericString(data) && isPositiveNumber(data.toInt()))
			return true
		return false
	}

	private fun callRecyclerView(numberList: List<String>) {
		recycler_view.adapter = ItemListAdapter(numberList)
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)
	}
}