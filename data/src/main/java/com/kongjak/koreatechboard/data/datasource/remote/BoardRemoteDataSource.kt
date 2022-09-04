package com.kongjak.koreatechboard.data.datasource.remote

import com.kongjak.koreatechboard.data.api.API
import com.kongjak.koreatechboard.data.model.BoardResponse
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

class BoardRemoteDataSource @Inject constructor(private val api: API) {
    suspend fun getBoard(site: String, board: String, page: Int): BoardResponse {
        return api.getBoard(site, board, page)
    }
}