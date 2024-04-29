package com.beemer.unofficial.fromis_9.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beemer.unofficial.fromis_9.model.dto.AlbumListDto
import com.beemer.unofficial.fromis_9.model.repository.AlbumRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumViewModel @Inject constructor(private val repository: AlbumRepository) : ViewModel() {
    private val _sortBy = MutableStateFlow(0)
    val sortBy: StateFlow<Int> = _sortBy

    private val _isDescending = MutableStateFlow(true)
    val isDescending: StateFlow<Boolean> = _isDescending

    private val _albumList = MutableStateFlow<List<AlbumListDto>?>(null)
    val albumList: StateFlow<List<AlbumListDto>?> = _albumList

    fun setSortBy(buttonGroup: Int) {
        _sortBy.value = buttonGroup
        sortAlbumList()
    }

    fun setDescending(isDescending: Boolean) {
        _isDescending.value = isDescending
        sortAlbumList()
    }

    fun getAlbumList() {
        viewModelScope.launch {
            _albumList.value = repository.getAlbumList()
            sortAlbumList()
        }
    }

    private fun sortAlbumList() {
        val sortedList = when (_sortBy.value) {
            0 -> _albumList.value?.sortedBy { it.release }?.toList()
            1 -> _albumList.value?.sortedBy { it.albumName.lowercase() }?.toList()
            2 ->_albumList.value?.sortedWith(compareBy<AlbumListDto> { it.type }.thenBy { it.albumName.lowercase() })?.toList()
            else -> _albumList.value
        }

        _albumList.value = if (_isDescending.value) sortedList?.reversed() else sortedList
    }
}