package com.example.gistslist.data.repositories

import androidx.lifecycle.MutableLiveData
import com.example.gistslist.models.data.pojo.GistBean
import com.example.gistslist.presentation.view.di.GetRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GistsRepository {
	private val gistsRetrofitService = GetRetrofit().getRetrofitService()

	//TODO тут самый непонятный момент. Я воспринимаю репозиторий как доп слой, для большей независимости вьюмодел,
// но тут я что то совсем не уверен в реализаци. У меня довольно странная цепочка: лист из маин активити подписан
// на лист из вью модел, а тот в свою очередь подписан на лист из репозитория. Я не придумал способа лучше, так как
// в loadGists идет вызов в отдельном потоке и работу во вью модел мне нужно продолжать только после того как этот поток
// закончит свою работу
	val pojoDataList: MutableLiveData<List<GistBean>> by lazy {
		MutableLiveData<List<GistBean>>()
	}

	fun loadGists() {
		val call = gistsRetrofitService.getGists()

		call.enqueue(object : Callback<List<GistBean>> {
			override fun onResponse(
				call: Call<List<GistBean>>?,
				response: Response<List<GistBean>>?
			) {
				pojoDataList.value = response?.body()
			}

			override fun onFailure(call: Call<List<GistBean>>, t: Throwable) {
				//TODO несколько коммитов назад я далал обработку ошибки при неудачном запросе, но потом удалил
				// Я правильно понимаю, вывод сообщения об ошибке, начинается отсюда
			}
		})
	}
}