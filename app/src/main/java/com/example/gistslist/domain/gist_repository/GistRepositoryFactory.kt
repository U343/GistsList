package com.example.gistslist.domain.gist_repository

import com.example.gistslist.data.repositories.GistsRepository

class GistRepositoryFactory {
	fun getRepository(): GistsRepository {
		return GistsRepository()
	}
}