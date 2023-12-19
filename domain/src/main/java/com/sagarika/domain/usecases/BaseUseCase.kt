package com.sagarika.domain.usecases

import com.sagarika.common.Failure
import com.sagarika.common.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch



interface BaseUseCase<Params, T> {
    operator fun invoke(params: Params): Flow<Result<T?>>
}
