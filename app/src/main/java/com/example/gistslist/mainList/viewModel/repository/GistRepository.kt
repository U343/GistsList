package com.example.gistslist.mainList.viewModel.repository

import androidx.lifecycle.MutableLiveData
import com.example.gistslist.gistsRetrofitModel.pojo.BasePojo
import com.example.gistslist.mainList.viewModel.di.GetRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GistRepository {
	private val gistsRetrofitService = GetRetrofit().getRetrofitService()

//TODO тут самый непонятный момент. Я воспринимаю репозиторий как доп слой, для большей независимости вьюмодел,
// но тут я что то совсем не уверен в реализаци. У меня довольно странная цепочка: лист из маин активити подписан
// на лист из вью модел, а тот в свою очередь подписан на лист из репозитория. Я не придумал способа лучше, так как
// в loadGists идет вызов в отдельном потоке и работу во вью модел мне нужно продолжать только после того как этот поток
// закончит свою работу
	val pojoDataList: MutableLiveData<List<BasePojo>> by lazy {
		MutableLiveData<List<BasePojo>>()
	}

	fun loadGists() {
		val call = gistsRetrofitService.getGists()

		call.enqueue(object : Callback<List<BasePojo>> {
			override fun onResponse(call: Call<List<BasePojo>>?, response: Response<List<BasePojo>>?) {
				pojoDataList.value = response?.body()
			}

			override fun onFailure(call: Call<List<BasePojo>>, t: Throwable) {
				//TODO несколько коммитов назад я далал обработку ошибки при неудачном запросе, но потом удалил
				// Я правильно понимаю, вывод сообщения об ошибке, начинается отсюда
			}
		})
	}
}