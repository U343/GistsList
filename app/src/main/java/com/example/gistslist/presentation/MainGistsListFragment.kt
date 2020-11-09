package com.example.gistslist.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.domain.gist_repository.GistRepositoryFactory
import com.example.gistslist.domain.gist_repository.IGistRepository
import com.example.gistslist.models.data.gist.GistModel
import com.example.gistslist.presentation.recycle_view.ItemListAdapter
import com.example.gistslist.presentation.view.MainFragmentModel
import com.example.gistslist.presentation.view.MainFragmentModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainGistsListFragment : Fragment() {
	private lateinit var viewModel: MainFragmentModel
	private lateinit var repositoryGistList: IGistRepository
	private lateinit var recyclerViewAdapter: ItemListAdapter

	companion object {
		fun newInstance(): MainGistsListFragment {
			return MainGistsListFragment()
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {
		return inflater.inflate(R.layout.activity_main, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initViewModelAndRepository()
		initRecyclerView()
		observeListForRecycleView()

		observeProgressBar()

		add_button.setOnClickListener {
			viewModel.getGistsList()
		}
	}

	private fun observeListForRecycleView() {
		val numberListObserver = Observer<ArrayList<GistModel>> { gistsList ->
			recyclerViewAdapter.setData(gistsList)
		}
		viewModel.gistsStringList.observe(this, numberListObserver)
	}

	private fun observeProgressBar() {
		val progress = Observer<Boolean> { loadDataStatus ->
			if (loadDataStatus) {
				progress_bar.visibility = View.VISIBLE
			} else {
				progress_bar.visibility = View.INVISIBLE
			}
		}
		viewModel.loadDataStatus.observe(this, progress)
	}

	private fun initViewModelAndRepository() {
		repositoryGistList = GistRepositoryFactory().getRepository()
		viewModel = ViewModelProvider(this, MainFragmentModelFactory(repositoryGistList)).get(
			MainFragmentModel::class.java
		)
	}

	private fun initRecyclerView() {
		recyclerViewAdapter = ItemListAdapter()

		recycler_view.adapter = recyclerViewAdapter
		recycler_view.layoutManager = LinearLayoutManager(requireContext())
		recycler_view.setHasFixedSize(true)
	}
}