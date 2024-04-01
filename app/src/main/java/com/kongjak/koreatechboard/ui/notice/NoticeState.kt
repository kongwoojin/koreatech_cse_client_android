package com.kongjak.koreatechboard.ui.notice

import com.kongjak.koreatechboard.domain.model.LocalArticle

data class NoticeState(
    val articles: List<LocalArticle> = emptyList()
)
