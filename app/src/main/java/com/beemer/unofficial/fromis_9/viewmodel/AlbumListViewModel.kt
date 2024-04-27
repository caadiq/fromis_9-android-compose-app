package com.beemer.unofficial.fromis_9.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AlbumListViewModel : ViewModel() {
    private val _sortBy = MutableStateFlow(0)
    val sortBy: StateFlow<Int> = _sortBy

    private val _isDescending = MutableStateFlow(true)
    val isDescending: StateFlow<Boolean> = _isDescending

    fun setSortBy(buttonGroup: Int) {
        _sortBy.value = buttonGroup
    }

    fun setDescending(isDescending: Boolean) {
        _isDescending.value = isDescending
    }
}