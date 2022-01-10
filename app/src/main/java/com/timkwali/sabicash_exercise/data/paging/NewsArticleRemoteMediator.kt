package com.timkwali.sabicash_exercise.data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.timkwali.sabicash_exercise.common.Constants
import com.timkwali.sabicash_exercise.data.local.NewsArticleDatabase
import com.timkwali.sabicash_exercise.data.remote.NewsArticleApi
import com.timkwali.sabicash_exercise.domain.model.NewsArticle
import com.timkwali.sabicash_exercise.domain.model.NewsArticleRemoteKeys
import java.lang.Exception
import javax.inject.Inject

@ExperimentalPagingApi
class NewsArticleRemoteMediator @Inject constructor(
    private val newsArticleApi: NewsArticleApi,
    private val newsArticleDatabase: NewsArticleDatabase
): RemoteMediator<Int, NewsArticle>() {

    private val newsArticleDao = newsArticleDatabase.newsArticleDao()
    private val newsArticleRemoteKeysDao = newsArticleDatabase.newsArticleRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, NewsArticle>
    ): MediatorResult {
        return try {
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = newsArticleApi
                .getNewsArticles(
                    pageNumber = currentPage,
                    perPage = Constants.ITEMS_PER_PAGE)
                .articles
//            for(index in response.indices) {
//                response[index].id = index
//            }

            val endOfPaginationReached = response.isEmpty()

            val prevPage = if(currentPage == 1) null else currentPage - 1
            val nextPage = if(endOfPaginationReached) null else currentPage + 1

            newsArticleDatabase.withTransaction {
                if(loadType == LoadType.REFRESH) {
                    newsArticleDao.deleteAllNewsArticles()
                    newsArticleRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.map { newsArticle ->
                    NewsArticleRemoteKeys(
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                newsArticleRemoteKeysDao.saveAllRemoteKeys(remoteKeys = keys)
                newsArticleDao.saveAllNewsArticles(newsArticles = response)
                Log.d("jfakfaf", response.toString())

            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, NewsArticle>
    ): NewsArticleRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                newsArticleRemoteKeysDao.getRemoteKeys(id = id)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, NewsArticle>
    ): NewsArticleRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { newsArticle ->
                newsArticleRemoteKeysDao.getRemoteKeys(id = newsArticle.id)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, NewsArticle>
    ): NewsArticleRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { newsArticle ->
                newsArticleRemoteKeysDao.getRemoteKeys(id = newsArticle.id)
            }
    }
}