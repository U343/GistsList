package com.example.gistslist.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.domain.application.GistRepositoryProvider
import com.example.gistslist.presentation.recycle_view.GistInfoAdapter
import com.example.gistslist.presentation.view_model.GistInfoViewModel
import com.example.gistslist.presentation.view_model.GistInfoViewModelFactory
import kotlinx.android.synthetic.main.gist_info_fragment.*

/**
 * Фрагмент отображения подробной информации о гисте
 *
 * @author Dmitrii Bondarev on 13.11.2020
 */
class GistInfoFragment : Fragment() {
	private lateinit var viewModel: GistInfoViewModel
	private lateinit var recyclerViewAdapter: GistInfoAdapter
	private var gistPosition: Int? = null

	companion object {
		const val TAG = "gist_info_fragment"
		const val KEY = "number_of_gist"

		fun newInstance(): GistInfoFragment {
			return GistInfoFragment()
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		gistPosition = arguments?.getInt(KEY)
		Log.d("frLifecycle", "GistInfo")
		return inflater.inflate(R.layout.gist_info_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val repository =
			(requireContext().applicationContext as GistRepositoryProvider).getRepositoryGistList()

		viewModel = ViewModelProvider(this, GistInfoViewModelFactory(repository))
			.get(GistInfoViewModel::class.java)

		initRecyclerView()
		observeItems()
		viewModel.generateGistInfoList(gistPosition)
		setNameGist()
	}

	private fun initRecyclerView() {
		recyclerViewAdapter = GistInfoAdapter()

		info_recycler_view.adapter = recyclerViewAdapter
		info_recycler_view.layoutManager = LinearLayoutManager(requireContext())
		info_recycler_view.setHasFixedSize(true)
	}

	private fun observeItems() {
		viewModel.gistInfoList.observe(this) {data -> recyclerViewAdapter.setData(data)}
	}

	private fun setNameGist() {
		gist_info_filename.text = viewModel.gistName ?: resources.getString(R.string.not_found)
	}
}