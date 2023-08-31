package com.kongjak.koreatechboard.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kongjak.koreatechboard.domain.usecase.GetDepartmentUseCase
import com.kongjak.koreatechboard.domain.usecase.GetDynamicThemeUseCase
import com.kongjak.koreatechboard.domain.usecase.SetDepartmentUseCase
import com.kongjak.koreatechboard.domain.usecase.SetDynamicThemeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getDepartmentUseCase: GetDepartmentUseCase,
    private val setDepartmentUseCase: SetDepartmentUseCase,
    private val getDynamicThemeUseCase: GetDynamicThemeUseCase,
    private val setDynamicThemeUseCase: SetDynamicThemeUseCase
) : ViewModel() {
    private val _department = MutableLiveData(0)
    val department: LiveData<Int>
        get() = _department

    private val _isDynamicTheme = MutableLiveData(true)
    val isDynamicTheme: LiveData<Boolean>
        get() = _isDynamicTheme

    init {
        getDepartment()
        getDynamicTheme()
    }

    private fun getDepartment() {
        viewModelScope.launch {
            getDepartmentUseCase().collectLatest {
                _department.value = it
            }
        }
    }

    fun setDepartment(index: Int) {
        viewModelScope.launch {
            setDepartmentUseCase(index)
        }
    }

    private fun getDynamicTheme() {
        viewModelScope.launch {
            getDynamicThemeUseCase().collectLatest {
                _isDynamicTheme.value = it
            }
        }
    }

    fun setDynamicTheme(state: Boolean) {
        viewModelScope.launch {
            setDynamicThemeUseCase(state)
        }
    }
}
