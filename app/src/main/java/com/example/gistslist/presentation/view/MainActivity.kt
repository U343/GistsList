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
		val fragment = MainGistsListFragment.newInstance()
		val fragmentManager = supportFragmentManager

		val fragmentTransaction = fragmentManager.beginTransaction()
		val newFragment = fragmentManager.findFragmentByTag(MainGistsListFragment.TAG) ?: fragment
		fragmentTransaction.replace(R.id.content_container, newFragment, MainGistsListFragment.TAG)
		fragmentTransaction.commit()
	}

	override fun goToGistInfoFragment(gistPosition: Int) {
		val fragment = GistInfoFragment.newInstance()
		val fragmentManager = supportFragmentManager
		val bundle = Bundle()

		bundle.putInt(GistInfoFragment.KEY, gistPosition)
		val fragmentTransaction = fragmentManager.beginTransaction()
		val newFragment = fragmentManager.findFragmentByTag(GistInfoFragment.TAG) ?: fragment
		fragmentTransaction.replace(R.id.content_container, newFragment, GistInfoFragment.TAG)
		newFragment.arguments = bundle
		fragmentTransaction.addToBackStack(null)
		fragmentTransaction.commit()
	}
}

