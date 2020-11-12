package com.example.gistslist.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.gistslist.R

class GistInfoFragment : Fragment() {

	companion object {
		fun newInstance(): GistInfoFragment {
			return GistInfoFragment()
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		Log.d("fragment_manage", "GistInfoFragment")
		return inflater.inflate(R.layout.gist_info_fragment, container, false)
	}
}