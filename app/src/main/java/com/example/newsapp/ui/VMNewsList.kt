package com.example.newsapp.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import androidx.paging.*
import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.GetNewsParam
import com.example.newsapp.data.remote.NewsList
import com.example.newsapp.domain.use_case.get_news.GetNewsUseCase
import com.example.newsapp.ui.mapper.NewsListPresentMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VMNewsList @Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val newsListPresentMapper: NewsListPresentMapper
) : ViewModel() {

    companion object {
        const val PER_PAGE = 5
    }

    var items = MediatorLiveData<PagingData<RecArticle>>()



    public fun getNews() {

        val loadedItems = Pager(PagingConfig(PER_PAGE)) {
            GetNewsPagingSource(
              ::loadNewsInPage,
                newsListPresentMapper,
                ::onClick
            )
        }.liveData.cachedIn(viewModelScope)
        items.addSource(loadedItems) {
            items.postValue(it)
        }
    }


    fun onClick(id:Int?){

    }

    private suspend fun loadNewsInPage(page: Int): Resource<NewsList> {
        return getNewsUseCase.buildUseCase(
            GetNewsParam(),page, PER_PAGE)
    }

}