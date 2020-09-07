package com.android.achmadfuadagustian.data.api.datasources.services

import com.android.achmadfuadagustian.data.api.datasources.dto.PageApiDto
import com.android.achmadfuadagustian.data.api.datasources.dto.RepositoryApiDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search/repositories")
    fun searchRepositories(@Query("q") q: String?,
                           @Query("page") page: Int): Call<PageApiDto<RepositoryApiDto>>

}