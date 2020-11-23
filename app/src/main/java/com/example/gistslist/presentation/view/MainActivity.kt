package com.example.gistslist.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.gistslist.R
import com.example.gistslist.presentation.router.GistListRouter

/**
 * Активити функционала отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainActivity : AppCompatActivity(), GistListRouter {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.empty_activity)

		if (savedInstanceState == null) {
			goToMainGistListFragment()
		}
	}

	override fun goToMainGistListFragment() {
		showFragment(MainGistsListFragment.newInstance(), false, MainGistsListFragment.TAG)
	}

	override fun goToGistInfoFragment(gistId: String) {
		showFragment(GistInfoFragment.newInstance(gistId), true, GistInfoFragment.TAG)
	}

	private fun showFragment(fragment: Fragment, toBackStack: Boolean, tag: String) {
		val fragmentManager = supportFragmentManager

		val fragmentTransaction = fragmentManager.beginTransaction()
		val newFragment = fragmentManager.findFragmentByTag(tag) ?: fragment
		fragmentTransaction.replace(R.id.content_container, newFragment, tag)
		if (toBackStack) {
			fragmentTransaction.addToBackStack(null)
		}
		fragmentTransaction.commit()
	}
}

