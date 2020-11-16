package com.example.gistslist.presentation.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gistslist.R
import com.example.gistslist.domain.application.GistRepositoryProvider
import com.example.gistslist.presentation.recycle_view.MainGistListAdapter
import com.example.gistslist.presentation.router.GistListRouter
import com.example.gistslist.presentation.view_model.MainFragmentViewModel
import com.example.gistslist.presentation.view_model.MainFragmentViewModelFactory
import kotlinx.android.synthetic.main.maint_gists_list_fragment.*
import java.lang.ref.WeakReference

/**
 * Фрагмент отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainGistsListFragment : Fragment() {
	private lateinit var viewModel: MainFragmentViewModel
	private lateinit var recyclerViewAdapter: MainGistListAdapter
	private lateinit var mRouter: WeakReference<GistListRouter>

	companion object {
		const val TAG = "main_fragment"

		fun newInstance(): MainGistsListFragment {
			return MainGistsListFragment()
		}
	}

	override fun onAttach(context: Context) {
		super.onAttach(context)

		val activity = requireActivity()
		if (activity is GistListRouter) {
			mRouter = WeakReference(activity);
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
		recyclerViewAdapter = MainGistListAdapter { position ->
			onListItemClick(position as Int)
		}

		gist_recycler_view.adapter = recyclerViewAdapter
		gist_recycler_view.layoutManager = LinearLayoutManager(requireContext())
		gist_recycler_view.setHasFixedSize(true)
	}

	/**
	 * Обработчик нажатия на элемент списка recycler view
	 */
	private fun onListItemClick(position: Int) {
		Toast.makeText(requireContext(), "click item $position", Toast.LENGTH_SHORT).show()
		mRouter.get()?.goToGistInfoFragment()
	}
}