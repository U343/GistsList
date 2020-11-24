package com.example.gistslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gistslist.R
import com.example.gistslist.domain.application.GistRepositoryProvider
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
	private var gistId: String? = null

	companion object {
		const val TAG = "gist_info_fragment"
		const val KEY = "number_of_gist"

		fun newInstance(gistId: String): GistInfoFragment {
			val fragment = GistInfoFragment()
			val bundle = Bundle()

			bundle.putString(KEY, gistId)
			fragment.arguments = bundle
			return fragment
		}
	}

	override fun onCreateView(
		inflater: LayoutInflater,
		container: ViewGroup?,
		savedInstanceState: Bundle?
	): View? {

		gistId = arguments?.getString(KEY)
		return inflater.inflate(R.layout.gist_info_fragment, container, false)
	}

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		initViewModelAndRepository()
		observeViewElements()
		observeAvatar()

		if (!viewModel.isDataLoaded) {
			viewModel.loadGistInfoModel(gistId)
		}
	}

	private fun observeViewElements() {
		viewModel.gistInfoModel.observe(this) { model ->
			author_login.text = model.authorLogin
			gist_info_title.text = model.gistName
			gist_info_type.text = model.gistType
			gist_info_language.text = model.gistLanguage
			gist_info_url.text = model.urlToGist
			gist_info_description.text = model.gistDescription
		}
	}

	private fun observeAvatar() {
		viewModel.userAvatar.observe(this) { picassoObject ->
			picassoObject?.into(author_avatar)
		}
	}

	private fun initViewModelAndRepository() {
		val repository =
			(requireContext().applicationContext as GistRepositoryProvider).getRepositoryGistList()

		viewModel = ViewModelProvider(this, GistInfoViewModelFactory(repository))
			.get(GistInfoViewModel::class.java)
	}
}