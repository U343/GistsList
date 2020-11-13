package com.example.gistslist.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gistslist.R
import com.example.gistslist.presentation.view_model.GistInfoViewModel

class GistInfoFragment : Fragment() {
	private lateinit var viewModel: GistInfoViewModel

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
		Log.d("fragment_manage", "onCreateView")
		return inflater.inflate(R.layout.gist_info_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel = ViewModelProvider(this).get(GistInfoViewModel::class.java)
	}
}