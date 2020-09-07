package com.android.achmadfuadagustian.data.interfaces.interactor.experimental

import kotlinx.coroutines.*
import com.android.achmadfuadagustian.data.interfaces.interactor.InteractorCallback
import kotlin.coroutines.CoroutineContext

/**
 * Interactor
 * Experimental [UseCase] implementation that utilize Kotlin Coroutines which is experimental itself
 * Will be used as main Interactor's super class once kotlin-coroutines is released as stable version
 */
abstract class Interactor<Params, Entity> : UseCase<Params, Entity> {

    fun executeAsync(
            context: CoroutineContext,
            params: Params?,
            callback: InteractorCallback<Entity>?): Job = GlobalScope.launch(context) {
        try {
            val data = executeAsync(params)
            callback?.onDataAvailable(data)
        } catch (e: CancellationException) {
            callback?.onCancelled()
        } catch (e: Exception) {
            callback?.onError(e)
        }
    }

    suspend fun executeAsync(params: Params?): Entity {
        return GlobalScope.async(Dispatchers.Default) { execute(params) }.await()
    }
}