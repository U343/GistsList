package com.example.gistslist.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
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

		/**
		 * Возвращает ссылка на данный фрагмент и создает bundle для передачи аргемента
		 *
		 * @param gistId идентификатор выбранного гиста, по которому будет загружен гист
		 * @return Ссылка на GistInfoFragment
		 */
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

		gist_info_group_all_views.visibility = View.GONE
		initViewModelAndRepository()
		observeViewElements()
		observeAvatar()
		observeProgressBar()

		if (!viewModel.isDataLoaded) {
			viewModel.loadGistInfoModel(gistId)
		}
	}

	private fun initViewModelAndRepository() {
		val repository =
			(requireContext().applicationContext as GistRepositoryProvider).getRepositoryGistList()

		viewModel = ViewModelProvider(this, GistInfoViewModelFactory(repository))
			.get(GistInfoViewModel::class.java)
	}

	/**
	 * Подписывает вью элементы на модлеь с информацией о гисте
	 *
	 * Изначально элементы скрыты на экране, при обновлении модели, элементы, которые удалось
	 * получить отображаются на экране
	 */
	private fun observeViewElements() {
		viewModel.gistInfoModel.observe(this) { model ->
			showViewIfNecessary(gist_info_author_login, model.authorLogin)
			showViewIfNecessary(gist_info_title, model.gistName)
			showViewIfNecessary(gist_info_type, model.gistType, gist_info_type_header)
			showViewIfNecessary(gist_info_language, model.gistLanguage, gist_info_language_header)
			showViewIfNecessary(gist_info_url, model.urlToGist, gist_info_url_header)
			showViewIfNecessary(gist_info_description, model.gistDescription, gist_info_description_header)
			showViewIfNecessary(gist_info_content, model.gistContent, gist_info_content_header)
		}
	}


	private fun showViewIfNecessary(view: TextView, content: String?, header: View? = null) {
		if (content != null && content != "") {
			view.text = content
			view.visibility = View.VISIBLE
			header?.let { header.visibility = View.VISIBLE }
		}
	}

	private fun observeAvatar() {
		viewModel.userAvatar.observe(this) { picassoObject ->
			picassoObject?.into(gist_info_author_avatar)
			gist_info_author_avatar.visibility = View.VISIBLE
		}
	}

	/**
	 * Включение и выключение прогресс бара, в зависимости от состоянии загрузки данных
	 */
	private fun observeProgressBar() {
		viewModel.loadDataStatus.observe(this) { status ->
			when (status) {
				true -> gist_info_progress_bar.visibility = View.VISIBLE
				false -> gist_info_progress_bar.visibility = View.INVISIBLE
			}
		}
	}
}