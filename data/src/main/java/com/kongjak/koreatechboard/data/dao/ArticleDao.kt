package com.kongjak.koreatechboard.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kongjak.koreatechboard.data.enity.Article
import java.util.UUID

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article ORDER BY is_notice DESC, write_date DESC, received_time DESC, num DESC")
    fun getAll(): List<Article>

    @Query("SELECT * FROM article WHERE uuid = :uuid")
    fun getArticle(uuid: UUID): Article

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(article: Article)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg articles: Article)

    @Query("UPDATE article SET read = :read WHERE uuid = :uuid")
    fun updateRead(uuid: UUID, read: Boolean)

    @Query("DELETE FROM article WHERE uuid = :uuid")
    fun delete(uuid: UUID)

    @Query("DELETE FROM article")
    fun deleteAll()
}
