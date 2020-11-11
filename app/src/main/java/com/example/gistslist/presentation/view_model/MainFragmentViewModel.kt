package com.example.gistslist.presentation.view_model

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gistslist.models.presentation.gist_model.GistModel
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.gist_repository.IGistRepository
import java.util.function.Consumer

/**
 * Вью модель для отображения списка гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class MainFragmentViewModel(private val repository: IGistRepository) : ViewModel() {
	val gistsStringList: MutableLiveData<ArrayList<GistModel>> by lazy {
		MutableLiveData<ArrayList<GistModel>>()
	}

	val loadDataStatus: MutableLiveData<Boolean> by lazy {
		MutableLiveData<Boolean>()
	}

	private val onResponseConsumer =
		Consumer<List<GistBean>> { pojoList -> generateGistsList(pojoList) }

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

	/**
	 * Формирование списка моделей гиста
	 *
	 * Внутри функции подавляется предупреждение, так как листы совпадают: [gistsStringList.value] -
	 * это арэй лист с [GistModel], и результат функции map это лист с [GistModel]
	 *
	 * @param pojoList список с POJO объектами гиста
	 */
	private fun generateGistsList(pojoList: List<GistBean>) {

		@Suppress("UNCHECKED_CAST")
		gistsStringList.value = pojoList.map {
			GistModel(
				it.files.keys.firstOrNull(),
				it.description
			)
		} as ArrayList<GistModel>
		loadDataStatus.value = false
	}
}