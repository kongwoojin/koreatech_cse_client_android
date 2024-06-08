package com.kongjak.koreatechboard.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.kongjak.koreatechboard.data.api.API
import com.kongjak.koreatechboard.domain.model.BoardData

class SearchTitlePagingSource(private val api: API, private val site: String, private val board: String, private val title: String) : PagingSource<Int, BoardData>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BoardData> {
        try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            val responseRaw = api.searchBoardWithTitle(site, board, title, page)

            if (responseRaw.code() != 200) {
                throw Exception("${responseRaw.code()}")
            }

            val response = responseRaw.body()?.boardData

            return LoadResult.Page(
                data = response!!,
                prevKey = if (page <= 1) null else page - 1,
                nextKey = if (page >= responseRaw.body()!!.lastPage) null else page + 1
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, BoardData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}