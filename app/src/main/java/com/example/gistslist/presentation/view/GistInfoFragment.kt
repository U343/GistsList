package com.example.gistslist.presentation.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.gistslist.R
import com.example.gistslist.domain.application.CustomApplicationFactory
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
		//TODO тут все сырое, можно не смотреть, просто тестирую 
		val repository = CustomApplicationFactory.getCustomApplication().repositoryGistList
		viewModel = ViewModelProvider(
			this,
			GistInfoViewModelFactory(repository)
		).get(GistInfoViewModel::class.java)

		viewModel.generateGistModel(1)
		gist_info_filename.text = viewModel.gistObject.value?.userLogin
	}
}