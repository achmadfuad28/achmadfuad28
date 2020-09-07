package com.android.achmadfuadagustian.presentation.viewmodel

import androidx.databinding.ObservableField
import com.android.achmadfuadagustian.data.interfaces.entities.search.RepositoryItemResult
import com.framework.base.BaseViewModel
import com.framework.common.extensions.changeNumberFormat

class ItemViewModel : BaseViewModel() {

    var bName = ObservableField<String>()
    var bOwnerName = ObservableField<String>()
    var bDescription = ObservableField<String>()
    var bWatchesCount = ObservableField<String>()
    var bLanguage = ObservableField<String>()

    fun resetData() {
        bName.set(null)
        bOwnerName.set(null)
        bLanguage.set(null)
        bDescription.set(null)
        bWatchesCount.set(null)
    }

    fun setData(data: RepositoryItemResult) {
        bName.set(data.name)
        bOwnerName.set(data.ownerName)
        bDescription.set(data.description)
        bLanguage.set(data.language)
        bWatchesCount.set(changeNumberFormat(data.watchersCount))
    }
}