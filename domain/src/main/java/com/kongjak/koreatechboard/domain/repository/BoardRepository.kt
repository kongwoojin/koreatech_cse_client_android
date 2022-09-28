package com.kongjak.koreatechboard.domain.repository

import com.kongjak.koreatechboard.domain.model.Board

interface BoardRepository {
    suspend fun getBoard(site: String, board: String, page: Int): Board
}
