package com.example.gistslist.presentation.view

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.domain.application.GistRepositoryProvider
import com.example.gistslist.domain.gist_list_item.GistsMainListListener
import com.example.gistslist.models.presentation.gist_model.GistModel
import com.example.gistslist.presentation.recycle_view.ItemListAdapter
import com.example.gistslist.presentation.view_model.MainFragmentViewModel
import com.example.gistslist.presentation.view_model.MainFragmentViewModelFactory
import kotlinx.android.synthetic.main.maint_gists_list_fragment.*

/**
 * Фрагмент отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistsListFragment : Fragment(), GistsMainListListener {
	private lateinit var viewModel: MainFragmentViewModel
	private lateinit var recyclerViewAdapter: ItemListAdapter

	companion object {
		private const val TAG = "gist_info_fragment"

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

		initViewModelAndRepository()
		initRecyclerView()

		observeListForRecycleView()
		observeProgressBar()

		add_button.setOnClickListener {
			viewModel.getGistsList()
		}
	}

	private fun observeListForRecycleView() {
		viewModel.gistsStringList.observe(this) { gistsList ->
			recyclerViewAdapter.setData(gistsList)
		}
	}

	/**
	 * Отображает прогресс бар, пока загружается список гистов
	 */
	private fun observeProgressBar() {
		viewModel.loadDataStatus.observe(this) { loadDataStatus ->
			if (loadDataStatus) {
				progress_bar.visibility = View.VISIBLE
			} else {
				progress_bar.visibility = View.INVISIBLE
			}
		}
	}

	private fun initViewModelAndRepository() {
		val repository =
			(requireContext().applicationContext as GistRepositoryProvider).getRepositoryGistList()

		viewModel = ViewModelProvider(
			this,
			MainFragmentViewModelFactory(repository)
		).get(
			MainFragmentViewModel::class.java
		)
	}

	private fun initRecyclerView() {
		recyclerViewAdapter = ItemListAdapter { position ->
			onItemClick(position as Int)
		}

		recycler_view.adapter = recyclerViewAdapter
		recycler_view.layoutManager = LinearLayoutManager(requireContext())
		recycler_view.setHasFixedSize(true)
	}

	/**
	 * Обработчик нажатия на элемент списка recycler view
	 */
	override fun onItemClick(position: Int) {
		Toast.makeText(requireContext(), "click item $position", Toast.LENGTH_SHORT).show()
		showFragment(GistInfoFragment.newInstance())
	}

	private fun showFragment(fragment: Fragment) {
		val fragmentManager = fragmentManager

		val fragmentTransaction = fragmentManager?.beginTransaction()
		val newFragment = fragmentManager?.findFragmentByTag(TAG) ?: fragment
		fragmentTransaction?.replace(R.id.content_container, newFragment, TAG)
		fragmentTransaction?.addToBackStack(null)
		fragmentTransaction?.commit()
	}
}