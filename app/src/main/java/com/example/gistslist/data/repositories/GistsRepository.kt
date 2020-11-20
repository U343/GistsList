package com.example.gistslist.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gistslist.data.gist_retrofit.query_interface.GistsApi
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.gist_repository.GistRepositoryApi
import com.example.gistslist.models.presentation.gist_model.GistModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Consumer
import kotlin.properties.Delegates

/**
 * Реализация репозитория для работы со списком гистов
 *
 * Репозиторий, загружает гисты через апи гита и хранит список загруженныхгисто в [gistsList]
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistsRepository(private val retrofit: GistsApi) : GistRepositoryApi {
	private lateinit var gistsList: ArrayList<GistModel>
	private lateinit var dispose: Disposable

	@RequiresApi(Build.VERSION_CODES.N)
	override fun loadGists(loadSuccess: Consumer<List<GistBean>>, loadFail: Consumer<Throwable>) {
		val call = retrofit.getGists()

		dispose = call.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread())
				.subscribe({ result ->
					if (result != null) {
						generateGistList(result)
					}
					result?.let { loadSuccess.accept(result) }
				}, {
					loadFail.accept(it)
				})
	}

	fun disposeItems() {
		dispose.dispose()
	}

//	override fun loadGists(loadSuccess: Consumer<List<GistBean>>, loadFail: Consumer<Throwable>) {
//		val call = retrofit.getGists()
//
//		call.enqueue(object : Callback<List<GistBean>> {
//			@RequiresApi(Build.VERSION_CODES.N)
//			override fun onResponse(
//				call: Call<List<GistBean>>?,
//				response: Response<List<GistBean>>?
//			) {
//				val result = response?.body()
//				if (result != null) {
//					generateGistList(result)
//				}
//				result?.let { loadSuccess.accept(it) }
//			}
//
//			@RequiresApi(Build.VERSION_CODES.N)
//			override fun onFailure(call: Call<List<GistBean>>, t: Throwable) {
//				loadFail.accept(t)
//			}
//		})
//	}

	/**
	 * Формирование списка моделей гиста
	 *
	 * Внутри функции подавляется предупреждение Unchecked cast, так как во время каста не может
	 * возникнуть ошибка. Функция map формирует лист с элементами [GistModel] внутри
	 *
	 * @param pojoList список с POJO объектами гиста
	 */
	private fun generateGistList(pojoList: List<GistBean>) {
		@Suppress("UNCHECKED_CAST")
		gistsList = pojoList.map {
			GistModel(
				it.files.keys.firstOrNull(),
				it.ownerBean.login,
				it.description,
				it.files[it.files.keys.firstOrNull()]?.language,
				it.files[it.files.keys.firstOrNull()]?.type,
				it.html_url
			)
		} as ArrayList<GistModel>
	}

	override fun getGistsList() : ArrayList<GistModel> {
		return gistsList
	}

	override fun getGistById(id: Int) : GistModel? {
		return gistsList.getOrNull(id)
	}
}