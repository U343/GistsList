package com.example.gistslist.data.repositories

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.domain.gist_retrofit.GetRetrofitService
import com.example.gistslist.domain.gist_repository.IGistRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Consumer

/**
 * Реализация репозитория для работы со списком гистов
 *
 * @author Dmitrii Bondarev on 10.11.2020
 */
class GistsRepository : IGistRepository {
	private val gistsRetrofitService = GetRetrofitService().getRetrofitService()

	override fun loadGists(loadSuccess: Consumer<List<GistBean>>, loadFail: Consumer<Throwable>) {
		val call = gistsRetrofitService.getGists()

		call.enqueue(object : Callback<List<GistBean>> {
			@RequiresApi(Build.VERSION_CODES.N)
			override fun onResponse(
				call: Call<List<GistBean>>?,
				response: Response<List<GistBean>>?
			) {
				response?.body()?.let { loadSuccess.accept(it) }
			}

			@RequiresApi(Build.VERSION_CODES.N)
			override fun onFailure(call: Call<List<GistBean>>, t: Throwable) {
				loadFail.accept(t)
			}
		})
	}
}