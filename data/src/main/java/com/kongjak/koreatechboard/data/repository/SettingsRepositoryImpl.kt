package com.kongjak.koreatechboard.data.repository

import com.kongjak.koreatechboard.data.datasource.local.SettingsLocalDataSource
import com.kongjak.koreatechboard.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val settingsLocalDataSource: SettingsLocalDataSource
) : SettingsRepository {
    override suspend fun setDepartment(index: Int) {
        settingsLocalDataSource.setDepartment(index)
    }
    override fun getDepartment(): Flow<Int> {
        return settingsLocalDataSource.getDepartment()
    }

    override suspend fun setDynamicTheme(state: Boolean) {
        settingsLocalDataSource.setDynamicTheme(state)
    }
    override fun getDynamicTheme(): Flow<Boolean> {
        return settingsLocalDataSource.getDynamicTheme()
    }

    override suspend fun setDarkTheme(theme: Int) {
        settingsLocalDataSource.setDarkTheme(theme)
    }

    override fun getDarkTheme(): Flow<Int> {
        return settingsLocalDataSource.getDarkTheme()
    }
}