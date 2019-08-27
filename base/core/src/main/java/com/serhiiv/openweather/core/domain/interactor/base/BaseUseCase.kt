package com.serhiiv.openweather.core.domain.interactor.base

import com.serhiiv.openweather.core.exception.Failure
import com.serhiiv.openweather.core.functional.Either
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

interface BaseUseCase<out T, in P> where T : Any {
    suspend fun execute(params: P): Either<Failure, T>

    operator fun invoke(
        scope: CoroutineScope,
        params: P,
        onResult: (Either<Failure, T>) -> Unit = {}
    ) {
        scope.launch { onResult(execute(params)) }
    }

    class NoParams
}