package com.android.achmadfuadagustian.data.api.mappers

import com.android.achmadfuadagustian.data.api.datasources.dto.PageApiDto
import com.android.achmadfuadagustian.data.api.datasources.dto.RepositoryApiDto
import com.android.achmadfuadagustian.data.interfaces.entities.search.RepositoryItemResult
import com.android.achmadfuadagustian.data.interfaces.entities.search.SearchRepositoriesResult
import com.framework.common.protocol.Mapper

class SearchRepositoriesMapper : Mapper<PageApiDto<RepositoryApiDto>, SearchRepositoriesResult> {

    override fun transform(from: PageApiDto<RepositoryApiDto>): SearchRepositoriesResult {
        val repositoryList = arrayListOf<RepositoryItemResult>()

        from.items?.let {
            for (item in it) {

                val repositoryResult = RepositoryItemResult(item.id)
                    .apply {
                        name = item.name
                        description = item.description
                        ownerAvatar = item.owner.avatar_url
                        ownerName = item.owner.login
                        language = item.language
                        watchersCount = item.watchersCount
                    }

                repositoryList.add(repositoryResult)
            }
        }

        return SearchRepositoriesResult().apply {
            data = repositoryList
            lastPage = from.incompleteResult
            count = from.totalCount
        }
    }
}