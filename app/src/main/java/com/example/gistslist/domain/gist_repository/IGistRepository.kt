package com.example.gistslist.domain.gist_repository

import androidx.lifecycle.MutableLiveData
import com.example.gistslist.models.data.pojo.GistBean

interface IGistRepository {
	val pojoDataList: MutableLiveData<List<GistBean>>

	fun loadGists()
}