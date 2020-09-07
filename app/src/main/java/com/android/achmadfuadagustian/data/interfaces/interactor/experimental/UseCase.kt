package com.android.achmadfuadagustian.data.interfaces.interactor.experimental

interface UseCase<Params, Entity> {

    @Throws(Exception::class)
    fun execute(params: Params?): Entity
}