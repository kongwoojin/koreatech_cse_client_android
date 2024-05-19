package com.kongjak.koreatechboard.ui.home

import androidx.lifecycle.viewModelScope
import com.kongjak.koreatechboard.domain.base.APIResult
import com.kongjak.koreatechboard.domain.usecase.api.GetBoardMinimumUseCase
import com.kongjak.koreatechboard.util.ViewModelExt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import java.util.concurrent.atomic.AtomicBoolean

class HomeBoardViewModel(private val getBoardMinimumUseCase: GetBoardMinimumUseCase) :
    ViewModelExt<HomeBoardState, HomeBoardEvent>(HomeBoardState()) {

    private val isFetching = AtomicBoolean(false)

    suspend fun getApi(department: String, board: String) = viewModelScope.launch {
        while (isFetching.get()) {
            delay(1000)
        }
        intent {
            if (board in state.boardData && state.boardData[board]?.boardData!!.isNotEmpty()) return@intent
            postSideEffect(HomeBoardEvent.FetchData(department, board))
        }
    }

    fun handleSideEffect(sideEffect: HomeBoardEvent) {
        when (sideEffect) {
            is HomeBoardEvent.FetchData -> {
                viewModelScope.launch {
                    isFetching.set(true)
                    intent {
                        reduce {
                            state.copy(
                                boardData = state.boardData + (
                                        sideEffect.board to (
                                                state.boardData[sideEffect.board]?.copy(
                                                    isLoaded = false
                                                ) ?: HomeBoardState.HomeBoardData(
                                                    isLoaded = false
                                                )
                                                )
                                        )
                            )
                        }
                    }

                    runCatching {
                        getBoardMinimumUseCase(sideEffect.department, sideEffect.board)
                    }.onSuccess {
                        when (it) {
                            is APIResult.Success -> {
                                intent {
                                    reduce {
                                        state.copy(
                                            boardData = state.boardData + (
                                                    sideEffect.board to (
                                                            state.boardData[sideEffect.board]?.copy(
                                                                isSuccess = true,
                                                                boardData = it.data.boardData
                                                                    ?: emptyList(),
                                                                statusCode = it.data.statusCode,
                                                                isLoaded = true
                                                            ) ?: HomeBoardState.HomeBoardData(
                                                                isSuccess = true,
                                                                boardData = it.data.boardData
                                                                    ?: emptyList(),
                                                                statusCode = it.data.statusCode,
                                                                isLoaded = true
                                                            )
                                                            )
                                                    )
                                        )
                                    }
                                }
                            }

                            is APIResult.Error -> {
                                intent {
                                    reduce {
                                        state.copy(
                                            boardData = state.boardData + (
                                                    sideEffect.board to (
                                                            state.boardData[sideEffect.board]?.copy(
                                                                isSuccess = false,
                                                                statusCode = it.errorType.statusCode,
                                                                error = it.errorType.statusCode.toString()
                                                            ) ?: HomeBoardState.HomeBoardData(
                                                                isSuccess = false,
                                                                statusCode = it.errorType.statusCode,
                                                                error = it.errorType.statusCode.toString()
                                                            )
                                                            )
                                                    )
                                        )
                                    }
                                }
                            }
                        }
                    }.onFailure {
                        intent {
                            reduce {
                                state.copy(
                                    boardData = state.boardData + (
                                            sideEffect.board to (
                                                    state.boardData[sideEffect.board]?.copy(
                                                        isSuccess = false,
                                                        error = it.localizedMessage ?: "",
                                                        isLoaded = true
                                                    ) ?: HomeBoardState.HomeBoardData(
                                                        isSuccess = false,
                                                        error = it.localizedMessage ?: "",
                                                        isLoaded = true
                                                    )
                                                    )
                                            )
                                )
                            }
                        }
                    }
                    isFetching.set(false)
                }
            }
        }
    }
}