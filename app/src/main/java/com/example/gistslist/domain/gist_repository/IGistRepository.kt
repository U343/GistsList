package com.example.gistslist.domain.gist_repository

import com.example.gistslist.models.data.pojo.GistBean
import java.util.function.Consumer

interface IGistRepository {
	fun loadGists(loadSuccess: Consumer<List<GistBean>>, loadFail: Consumer<Throwable>)
}