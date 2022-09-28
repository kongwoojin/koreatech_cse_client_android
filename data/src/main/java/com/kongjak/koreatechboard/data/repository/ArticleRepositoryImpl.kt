package com.kongjak.koreatechboard.data.repository

import com.kongjak.koreatechboard.data.datasource.remote.ArticleRemoteDataSource
import com.kongjak.koreatechboard.data.mapper.ArticleMapper
import com.kongjak.koreatechboard.domain.model.Article
import com.kongjak.koreatechboard.domain.repository.ArticleRepository
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(private val articleRemoteDataSource: ArticleRemoteDataSource) :
    ArticleRepository {
    override suspend fun getArticle(site: String, url: String): Article {
        return ArticleMapper.mapToArticle(articleRemoteDataSource.getArticle(site, url))
    }
}
