package com.example.gistslist.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.gistslist.R
import com.example.gistslist.data.cache_database.GistCacheDatabase
import com.example.gistslist.domain.application.GistRepositoryProvider
import com.example.gistslist.models.presentation.gist_model.GistInfoModel
import com.example.gistslist.presentation.view_model.GistInfoViewModel
import com.example.gistslist.presentation.view_model.GistInfoViewModelFactory
import com.squareup.picasso.RequestCreator
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
//TODO fix bug with clear fragment after turn phone
		//gist_info_group_all_views.visibility = View.GONE

		initViewModelAndRepository()
		observeViewElements()

		if (!viewModel.isDataLoaded) {
			viewModel.getGistInfo(gistId)
			Log.d("viewLifecycle", "yes")
		} else {
			Log.d("viewLifecycle", "no")
		}
	}

	private fun initViewModelAndRepository() {
		val repository =

			(requireContext().applicationContext as GistRepositoryProvider).getRepositoryGistList()

		viewModel = ViewModelProvider(this, GistInfoViewModelFactory(repository))
			.get(GistInfoViewModel::class.java)
	}

	private fun observeViewElements() {
		viewModel.gistInfoModel.observe(this) { model ->
			showViewElements(model)
			setUserAvatar(model.avatarUrl)
		}

		viewModel.loadDataStatus.observe(this) { status ->
			when (status) {
				true -> gist_info_progress_bar.visibility = View.VISIBLE
				false -> gist_info_progress_bar.visibility = View.INVISIBLE
			}
		}
	}

	/**
	 * Отображение необходимых элементов
	 *
	 * Изначально все поля с информацией о гисте скрыты, после загрузки информации о гисте,
	 * отображаются только необх, информацию о которых удалось получить
	 */
	private fun showViewElements(model: GistInfoModel) {
		showViewIfNecessary(gist_info_author_login, model.authorLogin)
		showViewIfNecessary(gist_info_title, model.gistName)
		showViewIfNecessary(gist_info_type, model.gistType, gist_info_type_header)
		showViewIfNecessary(gist_info_language, model.gistLanguage, gist_info_language_header)
		showViewIfNecessary(gist_info_url, model.urlToGist, gist_info_url_header)
		showViewIfNecessary(gist_info_description, model.gistDescription, gist_info_description_header)
		showViewIfNecessary(gist_info_content, model.gistContent, gist_info_content_header)
		gist_info_author_avatar.visibility = View.VISIBLE
	}

	private fun showViewIfNecessary(view: TextView, content: String?, header: View? = null) {
		if (content != null && content != "") {
			Log.d("viewLifecycle", "+")
			view.text = content
			view.visibility = View.VISIBLE
			header?.let { header.visibility = View.VISIBLE }
		}
	}

	private fun setUserAvatar(urlToAvatar: String?) {
		(requireContext().applicationContext as GistRepositoryProvider).loadImage()
			.load(urlToAvatar).into(gist_info_author_avatar)
	}
}