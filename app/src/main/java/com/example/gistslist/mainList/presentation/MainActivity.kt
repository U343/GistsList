package com.example.gistslist.mainList.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.gistModel.GistObject
import com.example.gistslist.mainList.viewModel.MainListViewModel
import com.example.gistslist.mainList.recycleView.ItemListAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.empty_activity)

		showFragment(MainGistsListFragment.newInstance(), false)
	}

	private fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
		val fragmentTransaction = supportFragmentManager.beginTransaction()

		fragmentTransaction.add(R.id.content_container, fragment)
		if (addToBackStack) {
			fragmentTransaction.addToBackStack(null)
		}
		fragmentTransaction.commit()
	}
}

