package com.example.gistslist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gistslist.R


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

