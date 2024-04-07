package com.anandbose.blogapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.anandbose.blogapp.BlogAppDelegate
import com.anandbose.blogapp.data.BlogEntryData
import com.anandbose.blogapp.data.RequestState
import com.anandbose.blogapp.repo.BlogRepository
import com.anandbose.blogapp.repo.WebBlogRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class HomeDataState(
    val state: RequestState,
    val list: List<BlogEntryData>? = null,
    val error: Exception? = null
)

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val httpClient = (application as BlogAppDelegate).httpClient
    private val repository: BlogRepository = WebBlogRepository(httpClient)
    private var requestState = RequestState.Idle
    private var blogList: List<BlogEntryData>? = null
    private var error: Exception? = null
    private val _stateFlow = MutableStateFlow(buildState())

    private fun buildState() = HomeDataState(requestState, blogList, error)

    val stateFlow: StateFlow<HomeDataState>
        get() = _stateFlow.asStateFlow()

    init {
        fetch()
    }

    fun fetch() {
        viewModelScope.launch {
            try {
                requestState = RequestState.Loading
                _stateFlow.emit(buildState())
                blogList = repository.getBlogEntries()
                requestState = RequestState.Success
                _stateFlow.emit(buildState())
            } catch (e: Exception) {
                e.printStackTrace()
                requestState = RequestState.Error
                error = e
                _stateFlow.emit(buildState())
            }
        }
    }
}