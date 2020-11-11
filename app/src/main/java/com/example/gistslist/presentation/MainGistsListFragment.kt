package com.example.gistslist.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.domain.gist_repository.GistRepositoryFactory
import com.example.gistslist.domain.gist_repository.IGistRepository
import com.example.gistslist.models.presentation.gist_model.GistModel
import com.example.gistslist.presentation.recycle_view.ItemListAdapter
import com.example.gistslist.presentation.view_model.MainFragmentViewModel
import com.example.gistslist.presentation.view_model.MainFragmentModelViewFactory
import kotlinx.android.synthetic.main.maint_gists_list_fragment.*

/**
 * Фрагмент отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistsListFragment : Fragment() {
	private lateinit var viewModel: MainFragmentViewModel
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
		return inflater.inflate(R.layout.maint_gists_list_fragment, container, false)
	}

	@RequiresApi(Build.VERSION_CODES.N)
	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)
		Log.d("fragment", "main fragment created")
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

	/**
	 * Отображает прогресс бар, пока загружается список гистов
	 */
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
		viewModel = ViewModelProvider(this, MainFragmentModelViewFactory(repositoryGistList)).get(
			MainFragmentViewModel::class.java
		)
	}

	private fun initRecyclerView() {
		recyclerViewAdapter = ItemListAdapter()

		recycler_view.adapter = recyclerViewAdapter
		recycler_view.layoutManager = LinearLayoutManager(requireContext())
		recycler_view.setHasFixedSize(true)
	}
}