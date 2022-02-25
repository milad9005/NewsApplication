package com.example.newsapp.domain.use_case.get_news

import com.example.newsapp.common.Resource
import com.example.newsapp.data.remote.GetNewsParam
import com.example.newsapp.data.remote.NewsList
import com.example.newsapp.domain.repository.NewsRepository
import com.reva.domain.usecase.base.UseCaseWithPaging
import kotlin.coroutines.CoroutineContext

class GetNewsUseCase(
    coroutineContext: CoroutineContext,
    private val repository: NewsRepository
) : UseCaseWithPaging<NewsList, GetNewsParam>(coroutineContext) {


    //
//    operator fun invoke(): Flow<Resource<List<RecArticleModel.ArticleModel>>> = flow {
//
//        try {
//            emit(Resource.Loading())
//            val news = repository.getNews().articles.map { it.toArticle() }
//            emit(Resource.Success(news))
//        } catch (e: HttpException) {
//            emit(Resource.Error(e.localizedMessage ?: "An unexpected error!"))
//        } catch (e: IOException) {
//            emit(Resource.Error("Couldn't reach server"))
//        }
//    }
    override suspend fun buildUseCase(
        params: GetNewsParam,
        page: Int,
        take: Int
    ): Resource<NewsList> {
        return repository.getNews(params, page, take)
    }


}