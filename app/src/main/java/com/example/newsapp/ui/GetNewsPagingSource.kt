package com.example.newsapp.ui

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.NewsList
import com.example.newsapp.ui.mapper.NewsListPresentMapper
import kotlin.reflect.KSuspendFunction1

class GetNewsPagingSource(
    private val loadNewsItem: KSuspendFunction1<@ParameterName(name = "page") Int, Resource<NewsList>>,
    private val newsListPresentMapper: NewsListPresentMapper,
    private val onClick:(Int?)->Unit,

) : PagingSource<Int, RecArticle>() {
    override fun getRefreshKey(state: PagingState<Int, RecArticle>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RecArticle> {
        val currentPageKey = params.key ?: 1
        when(val a = loadNewsItem(currentPageKey)){
            is Resource.Success<NewsList> ->{
                val advertiseListItem = a.model
                Log.i("PageNumbers", "load: $currentPageKey")
                return LoadResult.Page(
                    newsListPresentMapper.mapToPresentation(
                        advertiseListItem,
                        onClick = onClick
                    ).newsListItem,
                    prevKey = if (currentPageKey == 1) null else currentPageKey - 1,
                    nextKey = if (a.model.articleList.isEmpty()) null else currentPageKey + 1
                )
            }
            is Resource.NetworkError ->{ return LoadResult.Error(a.error)}
            is Resource.ServerError ->{ return LoadResult.Error(Throwable(""))}
            is Resource.UnknownError ->{ return LoadResult.Error(a.error)}
        }

    }

}


