package com.kongjak.koreatechboard.data.mapper

import com.kongjak.koreatechboard.data.enity.Article
import com.kongjak.koreatechboard.data.model.ArticleResponse
import com.kongjak.koreatechboard.domain.model.LocalArticle

fun List<Article>.mapToLocalArticle(): List<LocalArticle> {
    return this.map {
        LocalArticle(
            uuid = it.uuid,
            title = it.title,
            writer = it.writer,
            content = it.content,
            date = it.date,
            articleUrl = it.articleUrl,
            department = it.department,
            board = it.board,
            read = it.read
        )
    }
}

fun List<LocalArticle>.mapToArticle(): List<Article> {
    return this.map {
        Article(
            uuid = it.uuid,
            title = it.title,
            writer = it.writer,
            content = it.content,
            date = it.date,
            articleUrl = it.articleUrl,
            department = it.department,
            board = it.board,
            read = it.read
        )
    }
}

fun LocalArticle.mapToArticle(): Article {
    return Article(
        uuid = this.uuid,
        title = this.title,
        writer = this.writer,
        content = this.content,
        date = this.date,
        articleUrl = this.articleUrl,
        department = this.department,
        board = this.board,
        read = this.read
    )
}

fun Article.mapToLocalArticle(): LocalArticle {
    return LocalArticle(
        uuid = this.uuid,
        title = this.title,
        writer = this.writer,
        content = this.content,
        date = this.date,
        articleUrl = this.articleUrl,
        department = this.department,
        board = this.board,
        read = this.read
    )
}

fun ArticleResponse.mapToArticle(department: String, board: String): Article {
    return Article(
        uuid = this.uuid,
        title = this.title,
        writer = this.writer,
        content = this.content,
        date = this.date,
        articleUrl = this.articleUrl,
        department = department,
        board = board,
        read = false
    )
}
