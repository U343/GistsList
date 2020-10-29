package com.example.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.shoppinglist.mainList.viewModel.MainListViewModel
import com.example.shoppinglist.mainList.isNumericString
import com.example.shoppinglist.mainList.recycleView.ItemListAdapter
import com.example.shoppinglist.gistsRetrofitModel.launch.Common
import com.example.shoppinglist.gistsRetrofitModel.interfaceQuery.RetrofitServices
import com.example.shoppinglist.gistsRetrofitModel.pojo.BaseGist
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
	private lateinit var viewModel: MainListViewModel
	private var retrofitService: RetrofitServices? = null
	var gistsData: ArrayList<BaseGist> = ArrayList()


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		viewModel = ViewModelProvider(this).get(MainListViewModel::class.java)


		initRecyclerView()

		getGists()

		val numberListObserver = Observer<ArrayList<String>> { numberList ->
			recycler_view.adapter = ItemListAdapter(numberList)
			recycler_view.adapter?.notifyDataSetChanged()
		}

		add_button.setOnClickListener {
			clickAddButton()
		}
		viewModel.numberList.observe(this, numberListObserver)
	}

	private fun getGists() {
		retrofitService = Common.retrofitService
		val call = retrofitService?.getGists()
		call?.enqueue(object : Callback<ArrayList<BaseGist>> {
			override fun onResponse(call: Call<ArrayList<BaseGist>>?, response: Response<ArrayList<BaseGist>>?) {
				val newList = ArrayList<String>()
				gistsData = response?.body()!!
				for (elem in gistsData) {
					newList.add(elem.description)
				}
				recycler_view.adapter = ItemListAdapter(newList)
				recycler_view.adapter?.notifyDataSetChanged()
				//recyclerView.adapter = RecyclerViewAdapter(response?.body()!!, this@MainActivity)
			}

			override fun onFailure(call: Call<ArrayList<BaseGist>>, t: Throwable) {
			}
		})
	}

	private fun clickAddButton() {
		val inputData = getInputValue()

		if (isDataValid(inputData)) {
			viewModel.generateNumberList(inputData.toInt())
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

