package com.android.achmadfuadagustian.data.interfaces.entities.search

data class RepositoryItemResult (var id: Int) {
    var name: String? = null
    var description: String? = null
    var ownerName: String? = null
    var ownerAvatar: String? = null
    var watchersCount: Int = 0
    var language: String? = null
}