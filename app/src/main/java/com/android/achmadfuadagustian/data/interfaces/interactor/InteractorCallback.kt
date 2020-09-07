package com.android.achmadfuadagustian.data.interfaces.interactor


import com.android.achmadfuadagustian.data.interfaces.interactor.consumer.DataConsumer
import com.android.achmadfuadagustian.data.interfaces.interactor.consumer.ErrorConsumer

abstract class InteractorCallback<Entity> :
        DataConsumer<Entity>, ErrorConsumer {

    open fun onCancelled() {}
}
