package com.example.gistslist.presentation.view_model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.models.presentation.gist_model.GistModel
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import java.util.function.Consumer

/**
 * Вью модель для отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainFragmentViewModel(private val repository: GistRepositoryApi) : ViewModel() {
	val gistsStringList: MutableLiveData<List<GistModel>> by lazy {
		MutableLiveData<List<GistModel>>()
	}

	val loadDataStatus: MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}

	private val onResponseConsumer =
		Consumer<List<GistBean>> {
			gistsStringList.value = repository.getGistsList()
			loadDataStatus.value = false
		}

	private val onFailureConsumer =
		Consumer<Throwable> { Log.d("onFailure", "fail") }

	/**
	 * Вызов команды запроса к Api гита
	 */
	@RequiresApi(Build.VERSION_CODES.N)
	fun getGistsList() {
		loadDataStatus.value = true
		repository.loadGists(onResponseConsumer, onFailureConsumer)
	}
}