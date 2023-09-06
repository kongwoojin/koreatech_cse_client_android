package com.kongjak.koreatechboard.domain.usecase

import androidx.paging.PagingData
import com.kongjak.koreatechboard.domain.model.BoardData
import com.kongjak.koreatechboard.domain.repository.BoardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchBoardWithTitleUseCase @Inject constructor(private val boardRepository: BoardRepository) {
    operator fun invoke(site: String, board: String, title: String): Flow<PagingData<BoardData>> {
        return boardRepository.searchTitle(site, board, title)
    }
}
