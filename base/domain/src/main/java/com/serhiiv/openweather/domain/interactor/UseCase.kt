package com.serhiiv.openweather.domain.interactor

import com.serhiiv.openweather.domain.exception.Failure
import com.serhiiv.openweather.domain.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

abstract class UseCase<out T, in P> where T : Any {
    abstract suspend fun execute(params: P): Either<Failure, T>

    open operator fun invoke(
        scope: CoroutineScope,
        params: P,
        onResult: (Either<Failure, T>) -> Unit = {}
    ) {
        scope.launch { onResult(execute(params)) }
    }

    class None
}
