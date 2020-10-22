package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.mainList.recycleView.ItemListAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		val numberList = generateNumberList(5)

		recycler_view.adapter = ItemListAdapter(numberList)
		recycler_view.layoutManager = LinearLayoutManager(this)
		recycler_view.setHasFixedSize(true)

		//add_button.setOnClickListener()

		//val adapter = ShoppingListAdapter()
	}

	private fun	generateNumberList(size: Int) : List<String> {
		val numberList = ArrayList<String>()

		for (i in 0 until size) {
			numberList += i.toString()
		}
		return numberList
	}

}