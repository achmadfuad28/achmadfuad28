package com.android.achmadfuadagustian.data.interfaces.interactor.consumer

interface DataConsumer<T> {

    fun onDataAvailable(entity: T)
}