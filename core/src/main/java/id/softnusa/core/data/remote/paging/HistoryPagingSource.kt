package id.softnusa.core.data.remote.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import id.softnusa.core.data.mapper.transaction.toDomain
import id.softnusa.core.data.remote.api.TransactionApi
import id.softnusa.core.domain.model.response.transaction.DataHistory

class HistoryPagingSource(
    private val api: TransactionApi,
    private val search: String
) : PagingSource<Int, DataHistory>() {

    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, DataHistory> {

        return try {

            val page = params.key ?: 1
            val size = params.loadSize

            val response = api.getHistory(
                page = page,
                size = size,
                search = search
            )

            val domain = response.toDomain()

            LoadResult.Page(
                data = domain.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page < domain.meta.pageCount) page + 1 else null
            )

        } catch (e: Exception) {

            LoadResult.Error(e)

        }
    }

    override fun getRefreshKey(
        state: PagingState<Int, DataHistory>
    ): Int? {
        return state.anchorPosition
    }
}