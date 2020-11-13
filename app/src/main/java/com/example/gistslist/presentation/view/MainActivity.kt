package com.example.gistslist.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gistslist.R

/**
 * Активити функционала отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainActivity : AppCompatActivity() {
	private val tagMainFragment = "main_fragment"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.empty_activity)

		if (savedInstanceState == null) {
			showFragment(MainGistsListFragment.newInstance(), false)
		}
	}

	private fun showFragment(fragment: Fragment, addToBackStack: Boolean) {
		val fragmentManager = supportFragmentManager

		val fragmentTransaction = fragmentManager.beginTransaction()
		val newFragment = fragmentManager.findFragmentByTag(tagMainFragment) ?: fragment
		fragmentTransaction.replace(R.id.content_container, newFragment, tagMainFragment)
		if (addToBackStack) {
			fragmentTransaction.addToBackStack(null)
		}
		fragmentTransaction.commit()
	}
}

